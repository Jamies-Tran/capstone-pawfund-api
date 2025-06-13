package com.paw.fund.app.modules.shelter_assignment_management.aspect.handler;

import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import com.paw.fund.app.modules.pet_intake_registration_management.service.PetIntakeRegistrationCommandService;
import com.paw.fund.app.modules.pet_intake_registration_management.service.PetIntakeRegistrationQueryService;
import com.paw.fund.app.modules.shelter_assignment_management.aspect.ChangeShelterAssignmentStatusHelper;
import com.paw.fund.app.modules.shelter_assignment_management.aspect.NotifyHelper;
import com.paw.fund.app.modules.shelter_assignment_management.domain.ShelterAssignment;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentSearchCriteria;
import com.paw.fund.app.modules.shelter_assignment_management.service.ShelterAssignmentCommandService;
import com.paw.fund.app.modules.shelter_assignment_management.service.ShelterAssignmentQueryService;
import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import com.paw.fund.app.modules.shelter_management.service.ShelterQueryService;
import com.paw.fund.configuration.handler.exceptions.ServiceException;
import com.paw.fund.enums.EPetIntakeRegistrationStatus;
import com.paw.fund.enums.EShelterAssignmentStatus;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.websocket.MessageTemplateHandler;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterAssignmentAspectHandler {
    @NonNull
    ShelterAssignmentQueryService queryService;

    @NonNull
    ShelterAssignmentCommandService commandService;

    @NonNull
    PetIntakeRegistrationQueryService petIntakeRegistrationQueryService;

    @NonNull
    PetIntakeRegistrationCommandService petIntakeRegistrationCommandService;

    @NonNull
    ShelterQueryService shelterQueryService;

    @Around("@annotation(com.paw.fund.app.modules.shelter_assignment_management.aspect.GetShelterAssignmentDetailHelper)")
    public Object getShelterAssignmentDetailHelper(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            Object result = joinPoint.proceed();
            if(result instanceof ShelterAssignment shelterAssignment) {
                PetIntakeRegistration petIntakeRegistration = petIntakeRegistrationQueryService
                        .findById(shelterAssignment.petIntakeRegistrationId());
                Shelter shelter = shelterQueryService.findById(shelterAssignment.shelterId());

                return shelterAssignment
                        .withPetIntakeRegistration(petIntakeRegistration)
                        .withShelter(shelter);
            }

            throw new ServiceException();
        } catch (Throwable e) {
            throw e;
        }
    }

    @AfterReturning(
            returning = "result",
            pointcut = "@annotation(com.paw.fund.app.modules.shelter_assignment_management.aspect.NotifyHelper)"
    )
    public void notifyHelper(JoinPoint joinPoint, Object result) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        NotifyHelper annotation = methodSignature.getMethod().getAnnotation(NotifyHelper.class);
        if(result instanceof List<?> results) {
            List<ShelterAssignment> shelterAssignments = results.stream()
                    .map(x -> (ShelterAssignment) x)
                    .toList();

            shelterAssignments.forEach(x ->
                    MessageTemplateHandler.sendToTopic("%s/%s".formatted(annotation.variableAppDestination(), x.shelterId()), shelterAssignments));
        } else if(result instanceof ShelterAssignment shelterAssignment) {
            Page<ShelterAssignment> shelterAssignments = queryService
                    .findAll(ShelterAssignmentSearchCriteria.ofDefault(shelterAssignment.shelterId()), PageRequestCustom.of(0, 25, "updatedAt"));
            MessageTemplateHandler
                    .sendToTopic("%s/%s".formatted(annotation.variableAppDestination(), shelterAssignment.shelterId()), shelterAssignments.getContent());
        }
    }

    @AfterReturning(
            returning = "result",
            pointcut = "@annotation(com.paw.fund.app.modules.shelter_assignment_management.aspect.ChangeShelterAssignmentStatusHelper)"
    )
    public void changeShelterAssignmentStatusHelper(JoinPoint joinPoint, Object result) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        ChangeShelterAssignmentStatusHelper annotation = methodSignature.getMethod()
                .getAnnotation(ChangeShelterAssignmentStatusHelper.class);
        if(result instanceof ShelterAssignment shelterAssignment) {
            PetIntakeRegistration petIntakeRegistration = null;
            switch (annotation.status()) {
                case RECEIVED -> {
                    petIntakeRegistration = petIntakeRegistrationCommandService
                            .updateStatus(shelterAssignment.petIntakeRegistrationId(), EPetIntakeRegistrationStatus.PROCESSING, null);
                    commandService.updateStatusByPetIntakeRegistrationIdExceptById(petIntakeRegistration.petIntakeRegistrationId(), shelterAssignment.shelterAssignmentId(), EShelterAssignmentStatus.CANCELED, "Đã có trung tâm cứu hộ tiếp nhận");
                }

                case CANCELED -> {
                    petIntakeRegistration = petIntakeRegistrationCommandService
                            .updateStatus(shelterAssignment.petIntakeRegistrationId(), EPetIntakeRegistrationStatus.CANCEL, shelterAssignment.cancelReason());
                }

                case COMPLETED -> {
                    petIntakeRegistration = petIntakeRegistrationCommandService
                            .updateStatus(shelterAssignment.petIntakeRegistrationId(), EPetIntakeRegistrationStatus.FINISHED, null);
                }

            }

            Optional.ofNullable(petIntakeRegistration).ifPresent(x -> {
                String petIntakeRegistrationDestination = "/topic/pet-intake-registration/%s".formatted(x.informerPhone());
                List<PetIntakeRegistration> petIntakeRegistrations = petIntakeRegistrationQueryService
                        .findByPetIntakeRegistrationInformerPhone(x.informerPhone());
                MessageTemplateHandler.sendToTopic(petIntakeRegistrationDestination, petIntakeRegistrations);
            });

        }
    }
}

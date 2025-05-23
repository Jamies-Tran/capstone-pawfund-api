package com.paw.fund.app.modules.pet_intake_registration_management.aspect.handler;

import com.paw.fund.app.modules.map_management.repository.feign.data.PlaceDetail;
import com.paw.fund.app.modules.map_management.repository.feign.data.PlaceResult;
import com.paw.fund.app.modules.map_management.service.MapQueryService;
import com.paw.fund.app.modules.media_management.domain.verification.VerificationMedia;
import com.paw.fund.app.modules.media_management.service.verification.VerificationMediaCommandService;
import com.paw.fund.app.modules.media_management.service.verification.VerificationMediaQueryService;
import com.paw.fund.app.modules.pet_intake_registration_management.PetIntakeRegistrationModuleConstant;
import com.paw.fund.app.modules.pet_intake_registration_management.aspect.NotifyHelper;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationNotification;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationUpdate;
import com.paw.fund.app.modules.pet_intake_registration_management.service.PetIntakeRegistrationQueryService;
import com.paw.fund.app.modules.pet_management.domain.type.PetType;
import com.paw.fund.app.modules.pet_management.service.type.PetTypeQueryService;
import com.paw.fund.configuration.handler.exceptions.ServiceException;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.dto.CurrentAccountLogin;
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
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetIntakeAspectHandler {
    @NonNull
    PetIntakeRegistrationQueryService queryService;

    @NonNull
    VerificationMediaCommandService verificationMediaCommandService;

    @NonNull
    VerificationMediaQueryService verificationMediaQueryService;

    @NonNull
    MapQueryService mapQueryService;

    @NonNull
    PetTypeQueryService petTypeQueryService;

    @NonNull
    RequestContext requestContext;

    @Around("@annotation(com.paw.fund.app.modules.pet_intake_registration_management.aspect.CreatePetIntakeRegistrationParamHelper)")
    public Object PetIntakeCreateParamHelper(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object args = joinPoint.getArgs()[0];
            if(args instanceof PetIntakeRegistration petIntakeRegistration) {
                PlaceDetail placeDetail = mapQueryService.getPlaceDetailByPlaceId(petIntakeRegistration.placeId());
                PlaceResult placeResult = placeDetail.results().getFirst();
                CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
                PetIntakeRegistration newParam = petIntakeRegistration
                        .withAddress(placeResult.address())
                        .withLongitude(placeResult.placeGeometry().geometry().longitude())
                        .withLatitude(placeResult.placeGeometry().geometry().latitude())
                        .withAccountId(Optional.ofNullable(currentAccountLogin)
                                .map(CurrentAccountLogin::accountId)
                                .orElse(null));


                return joinPoint.proceed(new Object[] {newParam});
            }

            throw new ServiceException();
        } catch (Throwable e) {
            throw e;
        }
    }

    @AfterReturning(
            pointcut = "@annotation(com.paw.fund.app.modules.pet_intake_registration_management.aspect.CreatePetIntakeRegistrationParamHelper)",
            returning = "result"
    )
    public void createPetIntakeRegistrationHelper(JoinPoint joinPoint, Object result) {
        Object arg = joinPoint.getArgs()[0];
        if(result instanceof PetIntakeRegistration savedPetIntakeRegistration
                && arg instanceof PetIntakeRegistration argumentPetIntakeRegistration) {
            verificationMediaCommandService.saveAllWithPetIntakeRegistrationId(
                    savedPetIntakeRegistration.petIntakeRegistrationId(), argumentPetIntakeRegistration.medias());
        }
    }

    @Around("@annotation(com.paw.fund.app.modules.pet_intake_registration_management.aspect.GetPetIntakeRegistrationHelper)")
    public Object getPetIntakeRegistrationHelper(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            if(result instanceof PetIntakeRegistration petIntakeRegistration) {
                List<VerificationMedia> medias = verificationMediaQueryService
                        .findAllByPetIntakeRegistrationId(petIntakeRegistration.petIntakeRegistrationId());
                PetType petType = petTypeQueryService.findById(petIntakeRegistration.petTypeId());
                return petIntakeRegistration
                        .withMedias(medias)
                        .withPetType(petType);
            }

            throw new ServiceException();
        } catch (Throwable e) {
            throw e;
        }
    }

    @Around("@annotation(com.paw.fund.app.modules.pet_intake_registration_management.aspect.UpdatePetIntakeRegistrationHelper)")
    public Object updatePetIntakeRegistrationHelper(ProceedingJoinPoint joinPoint) throws Throwable  {
        try {
            Object arg = joinPoint.getArgs()[0];
            if( arg instanceof PetIntakeRegistrationUpdate update) {
                PetIntakeRegistration newPetIntakeRegistration = update.petIntakeRegistration();
                PlaceDetail placeDetail = mapQueryService.getPlaceDetailByPlaceId(newPetIntakeRegistration.placeId());
                PlaceResult placeResult = placeDetail.results().getFirst();
                PetIntakeRegistrationUpdate newArg = PetIntakeRegistrationUpdate.of(update.petIntakeRegistrationId(), newPetIntakeRegistration
                        .withAddress(placeResult.address())
                        .withLongitude(placeResult.placeGeometry().geometry().longitude())
                        .withLatitude(placeResult.placeGeometry().geometry().latitude()));
                Object result = joinPoint.proceed(new Object[] {newArg});
                if(result instanceof PetIntakeRegistration petIntakeRegistration) {
                    List<VerificationMedia> medias = verificationMediaCommandService
                            .updateAllWithPetIntakeRegistrationId(petIntakeRegistration.petIntakeRegistrationId(), newPetIntakeRegistration.medias());
                    return petIntakeRegistration.withMedias(medias);
                }

                throw new ServiceException();
            }
            throw new ServiceException();
        } catch (Throwable e) {
            throw e;
        }
    }

    @AfterReturning(
            pointcut = "@annotation(com.paw.fund.app.modules.pet_intake_registration_management.aspect.NotifyHelper)",
            returning = "result"
    )
    public void notifyHelper(JoinPoint joinPoint, Object result) {
        MethodSignature method = (MethodSignature) joinPoint.getSignature();
        NotifyHelper notifyHelper = method.getMethod().getAnnotation(NotifyHelper.class);
        if(StringUtils.hasText(notifyHelper.appDestination())) {
            List<PetIntakeRegistration> petIntakeRegistrations = queryService.findAllByStatusCodeNew();
            MessageTemplateHandler.sendToTopic(
                    notifyHelper.appDestination(),
                    PetIntakeRegistrationNotification.of(petIntakeRegistrations));
        }

        if(StringUtils.hasText(notifyHelper.variableDestination())) {
            if(result instanceof PetIntakeRegistration petIntakeRegistration) {
                String informerPhone = petIntakeRegistration.informerPhone();
                String completeDestination = notifyHelper.variableDestination().concat("/%s".formatted(informerPhone));
                List<PetIntakeRegistration> petIntakeRegistrations = queryService
                        .findByPetIntakeRegistrationInformerPhone(informerPhone);
                MessageTemplateHandler.sendToTopic(completeDestination, petIntakeRegistrations);
            }
        }

    }
}

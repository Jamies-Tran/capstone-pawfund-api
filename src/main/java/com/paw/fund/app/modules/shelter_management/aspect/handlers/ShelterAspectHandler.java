package com.paw.fund.app.modules.shelter_management.aspect.handlers;

import com.paw.fund.app.modules.account_management.domain.account.role.AccountRoleSummarizeInfo;
import com.paw.fund.app.modules.account_management.service.account.AccountQueryService;
import com.paw.fund.app.modules.account_management.service.account.role.AccountRoleQueryService;
import com.paw.fund.app.modules.form_management.service.form.reply.FormReplyQueryService;
import com.paw.fund.app.modules.mail_management.service.MailSenderCommandService;
import com.paw.fund.app.modules.map_management.repository.feign.data.PlaceComponent;
import com.paw.fund.app.modules.map_management.repository.feign.data.PlaceDetail;
import com.paw.fund.app.modules.map_management.service.MapQueryService;
import com.paw.fund.app.modules.media_management.service.common.CommonMediaCommandService;
import com.paw.fund.app.modules.media_management.service.common.CommonMediaQueryService;
import com.paw.fund.app.modules.pet_management.domain.pet.PetSummarizeInfo;
import com.paw.fund.app.modules.pet_management.domain.type.PetType;
import com.paw.fund.app.modules.pet_management.service.pet.PetQueryService;
import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import com.paw.fund.app.modules.shelter_management.domain.registration.ShelterRegistration;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationCreate;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationFilter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationNotification;
import com.paw.fund.app.modules.shelter_management.service.ShelterQueryService;
import com.paw.fund.app.modules.shelter_management.service.registration.ShelterRegistrationCommandService;
import com.paw.fund.app.modules.shelter_management.service.registration.ShelterRegistrationQueryService;
import com.paw.fund.app.modules.shelter_management.service.registration.ShelterRegistrationUseCaseService;
import com.paw.fund.configuration.handler.exceptions.RequestNotAvailable;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.configuration.handler.exceptions.ServiceException;
import com.paw.fund.common.context.request.RequestContext;
import com.paw.fund.enums.EShelterRegistrationStatus;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterAspectHandler {
    @NonNull
    ShelterRegistrationUseCaseService useCaseService;

    @NonNull
    AccountQueryService accountQueryService;

    @NonNull
    ShelterRegistrationCommandService registrationCommandService;

    @NonNull
    ShelterRegistrationQueryService registrationQueryService;

    @NonNull
    FormReplyQueryService formReplyQueryService;

    @NonNull
    ShelterQueryService shelterQueryService;

    @NonNull
    MailSenderCommandService mailSenderCommandService;

    @NonNull
    MapQueryService mapQueryService;

    @NonNull
    CommonMediaCommandService mediaCommandService;

    @NonNull
    CommonMediaQueryService commonMediaQueryService;

    @NonNull
    PetQueryService petQueryService;

    @NonNull
    AccountRoleQueryService accountRoleQueryService;

    @NonNull
    RequestContext requestContext;

    @NonFinal
    @Value("${app.mail.username}")
    String systemMail;

    @Around("@annotation(com.paw.fund.app.modules.shelter_management.aspect.CreateShelterRegistration)")
    public Object handleCreateShelterRegistration(ProceedingJoinPoint joinPoint) throws Throwable{
        Object result;
        Object args;
        try {
//            result = joinPoint.proceed();
//            args = joinPoint.getArgs()[0];
//            if(result instanceof Shelter shelter && args instanceof ShelterRegistrationCreate shelterRegistrationCreate) {
//                CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
//                validateCreateRegistrationAvailability(currentAccountLogin.accountId(),
//                        shelterRegistrationCreate.formResponseId());
//                if(Objects.isNull(shelter.shelterId())) {
//                    throw new ResourceNotValidException("Lỗi đăng ký trung tâm cứu trợ");
//                }
//                ShelterRegistration registration = ShelterRegistration.builder()
//                        .shelterId(shelter.shelterId())
//                        .accountId(currentAccountLogin.accountId())
//                        .formResponseId(shelterRegistrationCreate.formResponseId())
//                        .requestAt(LocalDateTime.now())
//                        .build();
//                ShelterRegistration saveShelterReg = registrationCommandService.save(registration);
//                ShelterRegistrationNotification notification = getRegistrationNotification();
//
//                MessageTemplateHandler.sendToTopic("/topic/get-shelter-registration-topic", notification);
//                return shelter.withShelterRegistration(saveShelterReg);
//            }
            throw new ResourceNotValidException("Lỗi đăng ký trung tâm cứu trợ");
        } catch (Throwable e) {
            throw e;
        }
    }

    private ShelterRegistrationNotification getRegistrationNotification() {
        ShelterRegistrationFilter filter = ShelterRegistrationFilter.prepareForAdmin();
        Page<ShelterRegistration> shelterRegistrations = registrationQueryService
                .findAll(filter.searchCriteria(), filter.pageRequestCustom());
        return ShelterRegistrationNotification.builder()
                .registrations(shelterRegistrations.getContent())
                .totalRegistrations(shelterRegistrations.getTotalElements())
                .build();

    }

    private void validateCreateRegistrationAvailability(Long accountId, Long formResponseId) {
        List<EShelterRegistrationStatus> registrationStatuses = List.of(EShelterRegistrationStatus.NEW,
                EShelterRegistrationStatus.RECEIVED, EShelterRegistrationStatus.APPROVED);

        if(registrationQueryService.existsByAccountIdAndStatusIn(accountId, registrationStatuses)) {
            throw new RequestNotAvailable("Không thể tạo thêm yêu cầu đăng ký trung tâm cứu trợ");
        }

        if(Objects.isNull(formResponseId) || !formReplyQueryService.existsByFormResponseId(formResponseId)) {
            throw new RequestNotAvailable("Chưa điền form đăng ký trung tâm");
        }
    }

    @AfterReturning(
            pointcut = "@annotation(com.paw.fund.app.modules.shelter_management.aspect.PublishRegistration)",
            returning = "result"
    )
    public void handlePublishRegistration(JoinPoint joinPoint, Object result) throws Throwable {
        try {
//            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//            PublishRegistration annotation = methodSignature.getMethod()
//                    .getAnnotation(PublishRegistration.class);
//            ShelterRegistrationNotification notification = useCaseService
//                    .getRegistrationNotification(ShelterRegistrationFilter.prepareForAdmin());
//            MessageTemplateHandler.sendToTopic("/topic/get-shelter-registration-topic", notification);
//            if(result instanceof ShelterRegistration shelterRegistration
//                    && StringUtils.hasText(annotation.sendTo())) {
//                Account account = accountQueryService.findById(shelterRegistration.accountId());
//                ShelterRegistration registration = registrationQueryService
//                        .findById(shelterRegistration.shelterRegistrationId());
//                MessageTemplateHandler.sendToUser(account.email(), annotation.sendTo(), registration);
//            }

        } catch (Throwable e) {
            throw e;
        }
    }

    @AfterReturning(
            pointcut = "@annotation(com.paw.fund.app.modules.shelter_management.aspect.SendMail)",
            returning = "result"
    )
    public void HandleSendMail(JoinPoint joinPoint, Object result) {
//        if(result instanceof ShelterRegistration shelterRegistration) {
//            Account account = accountQueryService.findById(shelterRegistration.accountId());
//            Shelter shelter = shelterQueryService.findById(shelterRegistration.shelterId());
//            MailSender mailSender = MailSender.builder()
//                    .from(systemMail)
//                    .to(account.email())
//                    .isHTMLSupport(true)
//                    .build();
//            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//            SendMail annotation = methodSignature.getMethod().getAnnotation(SendMail.class);
//            EShelterRegistrationStatus status = EShelterRegistrationStatus.findByCode(annotation.confirmContent());
//            switch (status) {
//                case RECEIVED -> mailSender = mailSender
//                        .prepareForEmailReceiveShelter(account.lastName(), shelter.shelterName());
//                case APPROVED -> mailSender = mailSender
//                        .prepareForEmailApproveShelter(account.lastName(), shelter.shelterName());
//                case REJECTED -> mailSender = mailSender.prepareForEmailRejectShelter(account.lastName(), shelter.shelterName(),
//                        shelterRegistration.rejectReason());
//                default -> throw new ResourceNotValidException();
//            }
//
//            mailSenderCommandService.sendMail(mailSender);
//        }
    }

    @Around("@annotation(com.paw.fund.app.modules.shelter_management.aspect.UpdateLocation)")
    public Object handleUpdateLocation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object arg = joinPoint.getArgs()[0];
        if(arg instanceof ShelterRegistrationCreate shelterRegistrationCreate) {
            if(Objects.isNull(shelterRegistrationCreate.placeId())
                    || Objects.isNull(shelterRegistrationCreate.shelter())) {
                throw new IllegalArgumentException("Place ID is required for updating location");
            }
            PlaceDetail placeDetail = mapQueryService.getPlaceDetailByPlaceId(shelterRegistrationCreate.placeId());
            Shelter shelter = shelterRegistrationCreate.shelter()
                    .withAddress(placeDetail.results().getFirst().address())
                    .withWard(getWardByComponent(placeDetail.results().getFirst().placeComponents()))
                    .withDistrict(getDistrictByComponent(placeDetail.results().getFirst().placeComponents()))
                    .withProvince(getProvinceByComponent(placeDetail.results().getFirst().placeComponents()))
                    .withLatitude(placeDetail.results().getFirst().placeGeometry().geometry().latitude())
                    .withLongitude(placeDetail.results().getFirst().placeGeometry().geometry().longitude());
            return joinPoint.proceed(new Object[]{shelterRegistrationCreate.withShelter(shelter)});
        }

        throw new IllegalArgumentException("Place ID is required for updating location");
    }

    private String getWardByComponent(List<PlaceComponent> placeComponents) {
        return placeComponents.get(1).placeName();
    }

    private String getDistrictByComponent(List<PlaceComponent> placeComponents) {
        return placeComponents.get(2).placeName();
    }

    private String getProvinceByComponent(List<PlaceComponent> placeComponents) {
        return placeComponents.get(3).placeName();
    }

    @Around("@annotation(com.paw.fund.app.modules.shelter_management.aspect.CreateShelterMedia)")
    public Object handleCreateShelterMedia(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
//          Object result = joinPoint.proceed();
//          Object args = joinPoint.getArgs()[0];
//          if(result instanceof Shelter shelter
//                  && args instanceof ShelterActive shelterActive) {
//            List<CommonMedia> commonMedias = mediaCommandService.saveAllWithShelterId(shelter.shelterId(),
//                    shelterActive.medias());
//
//            return shelter.withMedias(commonMedias);
//          }

          throw new ServiceException();
        } catch (Throwable e) {
            throw e;
        }
    }

    @Around("@annotation(com.paw.fund.app.modules.shelter_management.aspect.AttachMedia)")
    public Object handleAttachMedia(ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            Object result = joinPoint.proceed();
//            if(result instanceof Shelter shelter) {
//                List<CommonMedia> medias = commonMediaQueryService.findAllByShelterId(shelter.shelterId());
//
//                return shelter.withMedias(medias);
//            }
//            throw new ServiceException();
//        } catch (Throwable e) {
//            throw e;
//        }
        return null;
    }

    @Around("@annotation(com.paw.fund.app.modules.shelter_management.aspect.GetShelterListHelper)")
    public Object getShelterListHelper(ProceedingJoinPoint joinPoint) throws Throwable {
         try {
             Object result = joinPoint.proceed();
             if(result instanceof Page<?> results) {
                 Page<Shelter> shelters = results.map(x -> (Shelter) x);
                 List<Long> shelterIds = shelters.map(Shelter::shelterId).toList();
                 Map<Long, List<PetSummarizeInfo>> petSummarizeInfoMap = petQueryService.findAllPetSummarizeInfoByShelterIdIn(shelterIds)
                         .stream()
                         .collect(Collectors.groupingBy(PetSummarizeInfo::shelterId));
                 Map<Long, Integer> accountSummarizeInfoMap = accountRoleQueryService.findAllAccountRoleSummarizeInfoByShelterIdIn(shelterIds)
                         .stream()
                         .collect(Collectors.toMap(AccountRoleSummarizeInfo::shelterId, AccountRoleSummarizeInfo::totalStaff));
                 return shelters.map(x -> {
                     List<PetSummarizeInfo> petSummarizeInfos = petSummarizeInfoMap.computeIfAbsent(x.shelterId(), _ -> List.of());
                     Integer totalPets = petSummarizeInfos.stream()
                             .mapToInt(xx -> Optional.ofNullable(xx.total()).orElse(0))
                             .sum();
                     List<PetType> petTypes = petSummarizeInfos.stream()
                             .map(xx -> PetType.builder()
                                     .petTypeCode(Optional.ofNullable(xx.petTypeCode()).orElse(""))
                                     .petTypeName(Optional.ofNullable(xx.petTypeName()).orElse(""))
                                     .build())
                             .toList();
                     Integer totalStaff = accountSummarizeInfoMap.computeIfAbsent(x.shelterId(), _ -> 0);

                     return x
                             .withTotalPets(totalPets)
                             .withPetTypes(petTypes)
                             .withTotalStaff(totalStaff);
                 });
             }

             throw new ServiceException();
         } catch (Throwable e) {
             throw e;
         }
    }
}

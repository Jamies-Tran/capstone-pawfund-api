package com.paw.fund.app.modules.shelter_management.service.registration;

import com.paw.fund.app.modules.role_management.domain.Role;
import com.paw.fund.app.modules.shelter_management.annotation.PublishRegistration;
import com.paw.fund.app.modules.shelter_management.annotation.SendMail;
import com.paw.fund.app.modules.shelter_management.domain.registration.ShelterRegistration;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationFilter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationId;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationNotification;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationReject;
import com.paw.fund.app.modules.shelter_management.service.usecase.IShelterRegistrationUseCase;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.enums.ERole;
import com.paw.fund.enums.EShelterRegistrationStatus;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterRegistrationUseCaseService implements IShelterRegistrationUseCase {
    @NonNull
    ShelterRegistrationQueryService queryService;

    @NonNull
    ShelterRegistrationCommandService commandService;

    @NonNull
    RequestContext requestContext;

    @Override
    public ShelterRegistrationNotification getRegistrationNotification(ShelterRegistrationFilter filter) {
        List<String> accountRoleCodes = requestContext.getCurrentAccountLogin().roles()
                .stream()
                .map(Role::roleCode)
                .toList();
        if(accountRoleCodes.contains(ERole.ADMIN.getCode())) {
            Page<ShelterRegistration> shelterRegistrations = queryService
                    .findAll(filter.searchCriteria(), filter.pageRequestCustom());
            ShelterRegistrationNotification notification = ShelterRegistrationNotification.builder()
                    .registrations(shelterRegistrations.getContent())
                    .totalRegistrations(shelterRegistrations.getTotalElements())
                    .build();
            return notification;
        } else {
            return ShelterRegistrationNotification.ofEmpty();
        }

    }


    @Override
    @Transactional
    @PublishRegistration(appDestination = "/topic/get-shelter-registration-topic")
    @SendMail(confirmContent = "RECEIVED")
    public ShelterRegistration receiveShelterRegistration(ShelterRegistrationId shelterRegistrationId) {
        ValidationUtil.validateNotNullPointerException(shelterRegistrationId);
        return commandService.updateStatus(
                shelterRegistrationId.value(),
                EShelterRegistrationStatus.RECEIVED,
                null);
    }

    @Override
    @Transactional
    @PublishRegistration(appDestination = "/topic/get-shelter-registration-topic")
    @SendMail(confirmContent = "APPROVED")
    public ShelterRegistration approveShelterRegistration(ShelterRegistrationId shelterRegistrationId) {
        ValidationUtil.validateNotNullPointerException(shelterRegistrationId);
        return commandService.updateStatus(
                shelterRegistrationId.value(),
                EShelterRegistrationStatus.APPROVED,
                null);
    }

    @Override
    @Transactional
    @PublishRegistration(appDestination = "/topic/get-shelter-registration-topic")
    @SendMail(confirmContent = "REJECTED")
    public ShelterRegistration rejectedShelterRegistration(ShelterRegistrationReject shelterRegistrationReject) {
        ValidationUtil.validateNotNullPointerException(shelterRegistrationReject);
        return commandService.updateStatus(
                shelterRegistrationReject.shelterRegistrationId(),
                EShelterRegistrationStatus.REJECTED,
                shelterRegistrationReject.rejectReason());
    }
}

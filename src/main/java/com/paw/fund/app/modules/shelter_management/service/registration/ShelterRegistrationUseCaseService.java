package com.paw.fund.app.modules.shelter_management.service.registration;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.service.AccountQueryService;
import com.paw.fund.app.modules.account_role_management.domain.AccountRole;
import com.paw.fund.app.modules.account_role_management.service.AccountRoleQueryService;
import com.paw.fund.app.modules.role_management.domain.Role;
import com.paw.fund.app.modules.shelter_management.annotation.PublishRegistration;
import com.paw.fund.app.modules.shelter_management.annotation.SendMail;
import com.paw.fund.app.modules.shelter_management.domain.registration.ShelterRegistration;
import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterFilter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationEmail;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationFilter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationId;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationNotification;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationReject;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationSearchCriteria;
import com.paw.fund.app.modules.shelter_management.service.usecase.IShelterRegistrationUseCase;
import com.paw.fund.configuration.handler.exceptions.AuthenticationException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.dto.CurrentAccountLogin;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterRegistrationUseCaseService implements IShelterRegistrationUseCase {
    @NonNull
    ShelterRegistrationQueryService queryService;

    @NonNull
    ShelterRegistrationCommandService commandService;

    @NonNull
    AccountQueryService accountQueryService;

    @NonNull
    AccountRoleQueryService accountRoleQueryService;

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
    public ShelterRegistration getShelterRegistrationDetail(ShelterRegistrationEmail email) {
        Account account = accountQueryService.findByAccountEmail(email.value());

        return queryService.findByAccountId(account.accountId());
    }


    @Override
    @Transactional
    @PublishRegistration(
            messageMapping = "/topic/get-shelter-registration-topic",
            sendTo = "/queue/shelter-registration-detail"
    )
    @SendMail(confirmContent = "RECEIVED")
    public ShelterRegistration receiveShelterRegistration(ShelterRegistrationId shelterRegistrationId) {
        ValidationUtil.validateNotNullPointerException(shelterRegistrationId);
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();

        return commandService.updateStatusAndProcessById(
                shelterRegistrationId.value(),
                getProcessById(),
                EShelterRegistrationStatus.RECEIVED,
                null);
    }

    @Override
    @Transactional
    @PublishRegistration(
            messageMapping = "/topic/get-shelter-registration-topic",
            sendTo = "/queue/shelter-registration-detail"
    )
    @SendMail(confirmContent = "APPROVED")
    public ShelterRegistration approveShelterRegistration(ShelterRegistrationId shelterRegistrationId) {
        ValidationUtil.validateNotNullPointerException(shelterRegistrationId);
        return commandService.updateStatusAndProcessById(
                shelterRegistrationId.value(),
                getProcessById(),
                EShelterRegistrationStatus.APPROVED,
                null);
    }

    @Override
    @Transactional
    @PublishRegistration(
            messageMapping = "/topic/get-shelter-registration-topic",
            sendTo = "/queue/shelter-registration-detail"
    )
    @SendMail(confirmContent = "REJECTED")
    public ShelterRegistration rejectedShelterRegistration(ShelterRegistrationReject shelterRegistrationReject) {
        ValidationUtil.validateNotNullPointerException(shelterRegistrationReject);
        return commandService.updateStatusAndProcessById(
                shelterRegistrationReject.shelterRegistrationId(),
                getProcessById(),
                EShelterRegistrationStatus.REJECTED,
                shelterRegistrationReject.rejectReason());
    }

    @Override
    public Page<ShelterRegistration> getShelterRegistrationListProcessingByAccount(ShelterRegistrationFilter filter) {
        ShelterRegistrationSearchCriteria searchCriteria = filter.searchCriteria()
                .withProcessById(getProcessById());

        return queryService.findAll(searchCriteria, filter.pageRequestCustom());
    }

    private Long getProcessById() {
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        Role adminRole = currentAccountLogin.roles().stream()
                .filter(x -> Objects.equals(x.roleCode(), ERole.ADMIN.getCode()))
                .findAny()
                .orElseThrow(AuthenticationException::new);
        AccountRole accountRole = accountRoleQueryService
                .findByRoleIdAndAccountId(adminRole.roleId(), currentAccountLogin.accountId());

        return accountRole.accountRoleId();
    }
}

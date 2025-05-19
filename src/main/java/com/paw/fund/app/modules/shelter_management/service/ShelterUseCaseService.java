package com.paw.fund.app.modules.shelter_management.service;

import com.paw.fund.app.modules.account_management.service.account.AccountQueryService;
import com.paw.fund.app.modules.account_management.domain.account.role.AccountRole;
import com.paw.fund.app.modules.account_management.service.account.role.AccountRoleCommandService;
import com.paw.fund.app.modules.account_management.service.account.role.AccountRoleQueryService;
import com.paw.fund.app.modules.account_management.domain.role.Role;
import com.paw.fund.app.modules.account_management.service.role.usecase.IRoleUseCase;
import com.paw.fund.app.modules.shelter_management.aspect.AttachMedia;
import com.paw.fund.app.modules.shelter_management.aspect.CreateShelterMedia;
import com.paw.fund.app.modules.shelter_management.aspect.CreateShelterRegistration;
import com.paw.fund.app.modules.shelter_management.aspect.GetShelterListHelper;
import com.paw.fund.app.modules.shelter_management.aspect.UpdateLocation;
import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterActive;
import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterFilter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterId;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationCreate;
import com.paw.fund.app.modules.shelter_management.service.registration.ShelterRegistrationQueryService;
import com.paw.fund.app.modules.shelter_management.service.usecase.IShelterUseCase;
import com.paw.fund.configuration.handler.exceptions.AuthenticationException;
import com.paw.fund.configuration.handler.exceptions.RequestNotAvailable;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.dto.CurrentAccountLogin;
import com.paw.fund.enums.ERole;
import com.paw.fund.enums.EShelterRegistrationStatus;
import com.paw.fund.enums.EShelterStatus;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterUseCaseService implements IShelterUseCase {
    @NonNull
    IRoleUseCase roleUseCase;

    @NonNull
    ShelterCommandService commandService;

    @NonNull
    ShelterQueryService queryService;

    @NonNull
    AccountQueryService accountQueryService;

    @NonNull
    AccountRoleCommandService accountRoleCommandService;

    @NonNull
    AccountRoleQueryService accountRoleQueryService;

    @NonNull
    ShelterRegistrationQueryService shelterRegistrationQueryService;

    @NonNull
    RequestContext requestContext;

    @Override
    @Transactional
    @CreateShelterRegistration
    @UpdateLocation
    public Shelter registerShelter(ShelterRegistrationCreate shelterRegistrationCreate) {
        ValidationUtil.validateNotNullPointerException(shelterRegistrationCreate);
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        Optional<Shelter> foundShelter = queryService.findByAccountIdNullable(currentAccountLogin.accountId());
        if(foundShelter.isPresent()) {
            return commandService
                    .update(foundShelter.get().shelterId(), shelterRegistrationCreate.shelter());
        }
        List<String> roleCodes = currentAccountLogin.roles().stream().map(Role::roleCode).toList();
        validateCreateShelterAvailability(currentAccountLogin.accountId(), roleCodes);
        Shelter shelter = shelterRegistrationCreate.shelter();
        Shelter updateStatusShelter = shelter
                .withStatusCode(EShelterStatus.DRAFT.getCode())
                .withStatusName(EShelterStatus.DRAFT.getName());

        return commandService.save(updateStatusShelter);
    }

    @Override
    @Transactional
    @CreateShelterMedia
    public Shelter activeShelter(ShelterActive shelterActive) {
        ValidationUtil.validateNotNullPointerException(shelterActive.shelterId());
        validateActiveShelter(shelterActive.shelterId());

        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        Long adminRoleId = roleUseCase.getAdminRole().roleId();
        Long accountRoleId;

        Optional<AccountRole> existsAccountRole = accountRoleQueryService
                .findByRoleIdAndAccountIdNullable(adminRoleId, currentAccountLogin.accountId());
        if(existsAccountRole.isPresent()) {
            accountRoleId = existsAccountRole.get().accountRoleId();
        } else {
            AccountRole accountRole = accountRoleCommandService.save(currentAccountLogin.accountId(),
                    roleUseCase.getShelterOwnerRole().roleId());
            accountRoleId = accountRole.accountRoleId();
        }

        return commandService.updateStatusAndAccountRoleIdAndDescription(
                shelterActive.shelterId(),
                accountRoleId,
                shelterActive.description(),
                shelterActive.maximumPetCapacity(),
                EShelterStatus.ENABLE);
    }

    @Override
    @GetShelterListHelper
    public Page<Shelter> getShelterList(ShelterFilter shelterFilter) {
        return queryService.findAll(shelterFilter.searchCriteria(), shelterFilter.pageRequestCustom());
    }

    @Override
    @AttachMedia
    public Shelter getShelterDetail(ShelterId shelterId) {
        return queryService.findById(shelterId.value());
    }

    @Override
    public Page<Shelter> getShelterDistanceList(ShelterFilter shelterFilter) {
        return queryService.findAllDistance(shelterFilter.searchCriteria(), shelterFilter.pageRequestCustom());
    }

    private void validateActiveShelter(Long shelterId) {
        if(!shelterRegistrationQueryService.existsApprovedShelterRegistrationByShelterId(shelterId)) {
            throw new RequestNotAvailable("Yêu cầu chưa được duyệt");
        }
    }

    private void validateCreateShelterAvailability(Long accountId, List<String> roleCodes) {

        if(accountQueryService.existsAnyShelterById(accountId)) {
            throw new RequestNotAvailable("Tài khoản không thể tạo thêm trung tâm cứu trợ");
        } else if(accountQueryService.existsAnyShelterRegistrationByIdAndStatusCodeNot(
                accountId,
                EShelterRegistrationStatus.REJECTED.getCode())) {
            throw new RequestNotAvailable("Đăng ký trung tâm cứu trợ không hợp lệ");
        } else if(!CollectionUtils.isEmpty(roleCodes)
                && !roleCodes.contains(ERole.SHELTER_OWNER.getCode())) {
            throw new AuthenticationException("Bạn không có quyền tạo trung tâm cứu trợ");
        }
    }
}

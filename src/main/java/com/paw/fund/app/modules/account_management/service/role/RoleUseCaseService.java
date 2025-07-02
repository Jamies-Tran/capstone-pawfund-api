package com.paw.fund.app.modules.account_management.service.role;

import com.paw.fund.app.modules.account_management.domain.role.Role;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountId;
import com.paw.fund.app.modules.account_management.domain.role.usecase.data.transfer.RoleCode;
import com.paw.fund.app.modules.account_management.domain.role.usecase.IRoleUseCase;
import com.paw.fund.app.modules.account_management.domain.role.usecase.data.transfer.RoleCodeList;
import com.paw.fund.common.aspect.annotation.validate.args.ValidateArgs;
import com.paw.fund.enums.ERole;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleUseCaseService implements IRoleUseCase {
    @NonNull
    RoleQueryService queryService;

    @Override
    @Transactional(readOnly = true)
    @ValidateArgs
    public Role getRole(RoleCode roleCode) {
        return queryService.findByCode(roleCode.value());
    }

    @Override
    @Transactional(readOnly = true)
    public Role getShelterOwnerRole() {
        ERole donorRole = ERole.SHELTER_OWNER;
        return getRole(RoleCode.of(donorRole.getCode()));
    }

    @Override
    @Transactional(readOnly = true)
    public Role getDonorRole() {
        ERole donorRole = ERole.DONOR;
        return getRole(RoleCode.of(donorRole.getCode()));
    }

    @Override
    @Transactional(readOnly = true)
    public Role getAdaptorRole() {
        ERole adaptorRole = ERole.ADOPTER;
        return getRole(RoleCode.of(adaptorRole.getCode()));
    }

    @Override
    @Transactional(readOnly = true)
    public Role getAdminRole() {
        ERole adaptorRole = ERole.ADMIN;
        return getRole(RoleCode.of(adaptorRole.getCode()));
    }

    @Override
    @Transactional(readOnly = true)
    public Role getStaffRole() {
        ERole staffRole = ERole.STAFF;

        return getRole(RoleCode.of(staffRole.getCode()));
    }

    @Override
    @Transactional(readOnly = true)
    @ValidateArgs
    public List<Role> getRoleInCodeList(RoleCodeList roleCodeList) {
        return queryService.findAllByCodeIn(roleCodeList.value());
    }

    @Override
    @Transactional(readOnly = true)
    @ValidateArgs
    public List<Role> getRoleByAccountId(AccountId accountId) {
        return queryService.findAllByAccountId(accountId.value());
    }
}

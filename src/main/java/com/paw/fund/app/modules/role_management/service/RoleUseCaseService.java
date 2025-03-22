package com.paw.fund.app.modules.role_management.service;

import com.paw.fund.app.modules.role_management.domain.Role;
import com.paw.fund.app.modules.role_management.domain.usecase.RoleCode;
import com.paw.fund.app.modules.role_management.service.usecase.IRoleUseCase;
import com.paw.fund.enums.ERole;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleUseCaseService implements IRoleUseCase {
    @NonNull
    RoleQueryService queryService;

    @Override
    public Role getRole(RoleCode roleCode) {
        ValidationUtil.validateNotNullPointerException(roleCode);
        return queryService.findByCode(roleCode.value());
    }

    @Override
    public Role getDonorRole() {
        ERole donorRole = ERole.DONOR;
        return getRole(RoleCode.of(donorRole.getCode()));
    }

    @Override
    public Role getAdaptorRole() {
        ERole adaptorRole = ERole.ADOPTER;
        return getRole(RoleCode.of(adaptorRole.getCode()));
    }

    @Override
    public Role getAdminRole() {
        ERole adaptorRole = ERole.ADMIN;
        return getRole(RoleCode.of(adaptorRole.getCode()));
    }

    @Override
    public Role getStaffRole() {
        ERole staffRole = ERole.STAFF;

        return getRole(RoleCode.of(staffRole.getCode()));
    }
}

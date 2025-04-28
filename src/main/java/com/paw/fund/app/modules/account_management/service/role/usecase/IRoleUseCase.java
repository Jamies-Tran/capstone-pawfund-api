package com.paw.fund.app.modules.account_management.service.role.usecase;

import com.paw.fund.app.modules.account_management.domain.role.Role;
import com.paw.fund.app.modules.account_management.domain.usecase.role.RoleCode;

public interface IRoleUseCase {
    Role getRole(RoleCode roleCode);

    Role getShelterOwnerRole();

    Role getDonorRole();

    Role getAdaptorRole();

    Role getAdminRole();

    Role getStaffRole();
}

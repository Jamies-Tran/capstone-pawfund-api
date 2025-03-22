package com.paw.fund.app.modules.role_management.service.usecase;

import com.paw.fund.app.modules.role_management.domain.Role;
import com.paw.fund.app.modules.role_management.domain.usecase.RoleCode;

public interface IRoleUseCase {
    Role getRole(RoleCode roleCode);

    Role getDonorRole();

    Role getAdaptorRole();

    Role getAdminRole();

    Role getStaffRole();
}

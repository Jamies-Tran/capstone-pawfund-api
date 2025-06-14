package com.paw.fund.app.modules.account_management.domain.role.usecase;

import com.paw.fund.app.modules.account_management.domain.role.Role;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountId;
import com.paw.fund.app.modules.account_management.domain.role.usecase.data.transfer.RoleCode;
import com.paw.fund.app.modules.account_management.domain.role.usecase.data.transfer.RoleCodeList;

import java.util.List;

public interface IRoleUseCase {
    Role getRole(RoleCode roleCode);

    Role getShelterOwnerRole();

    Role getDonorRole();

    Role getAdaptorRole();

    Role getAdminRole();

    Role getStaffRole();

    List<Role> getRoleInCodeList(RoleCodeList roleCodeList);

    List<Role> getRoleByAccountId(AccountId accountId);
}

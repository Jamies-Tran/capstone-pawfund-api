package com.paw.fund.app.modules.account_role_management.domain;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.role_management.domain.Role;
import lombok.Builder;

@Builder
public record AccountRole(
        Long accountRoleId,
        Long roleId,
        Long accountId,
        Account account,
        Role role
) {
}

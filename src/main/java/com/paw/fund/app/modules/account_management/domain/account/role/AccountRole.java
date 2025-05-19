package com.paw.fund.app.modules.account_management.domain.account.role;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.role.Role;
import lombok.Builder;

@Builder
public record AccountRole(
        Long accountRoleId,
        Long roleId,
        Long accountId,
        Long shelterId,
        Account account,
        Role role
) {
}

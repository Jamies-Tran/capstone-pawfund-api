package com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.role.Role;
import com.paw.fund.enums.ERole;
import lombok.Builder;

import java.util.List;

@Builder
public record AccountRegisterRole(Account account, List<Role> roles) {
    public static AccountRegisterRole of(Account account, List<ERole> roles) {
        return AccountRegisterRole.builder()
                .account(account)
                .roles(roles.stream().map(x -> Role.builder().roleCode(x.getCode()).build()).toList())
                .build();
    }
}

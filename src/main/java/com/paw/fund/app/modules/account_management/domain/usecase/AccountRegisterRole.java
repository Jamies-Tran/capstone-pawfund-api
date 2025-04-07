package com.paw.fund.app.modules.account_management.domain.usecase;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.role_management.domain.Role;
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

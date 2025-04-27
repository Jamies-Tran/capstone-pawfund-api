package com.paw.fund.dto;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.role.Role;
import lombok.Builder;
import lombok.With;

import java.util.List;

@Builder
public record CurrentAccountLogin(
        Long accountId,
        String email,
        String phone,
        String fullName,
        @With List<Role> roles
) {
    public static CurrentAccountLogin of(Account account) {
        return CurrentAccountLogin.builder()
                .accountId(account.accountId())
                .email(account.email())
                .phone(account.phone())
                .fullName("%s %s".formatted(account.firstName(), account.lastName()))
                .build();
    }
}

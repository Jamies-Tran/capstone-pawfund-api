package com.paw.fund.dto;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.role_management.domain.Role;
import lombok.Builder;

@Builder
public record CurrentAccountLogin(
        Long accountId,
        String email,
        String phone,
        String fullName,
        Role role
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

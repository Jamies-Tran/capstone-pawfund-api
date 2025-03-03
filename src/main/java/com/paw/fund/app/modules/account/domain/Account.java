package com.paw.fund.app.modules.account.domain;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record Account(
        Long accountId,
        String firstName,
        String lastName,
        String identification,
        String email,
        String password,
        String phone,
        String address,
        LocalDate dateOfBirth,
        String genderCode,
        String genderName,
        String roleCode,
        String roleName,
        String statusCode,
        String statusName
) {
}

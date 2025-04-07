package com.paw.fund.app.modules.account_management.controller.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AccountRoleRegisterRequest(
        LocalDate dateOfBirth,
        String genderCode,
        String genderName,
        @NotNull(message = "Vui lòng nhập địa chỉ")
        String address,
        @NotNull(message = "Vui lòng nhập CCCD")
        String identification
) {
}

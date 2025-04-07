package com.paw.fund.app.modules.account_management.controller.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AccountRoleRegisterRequest(
        @Schema(description = "Ngày sinh của người dùng", example = "1997-01-02")
        LocalDate dateOfBirth,
        @Schema(description = "Mã giới tính", example = "MALE")
        String genderCode,
        @Schema(description = "Tên giới tính", example = "Nam")
        String genderName,
        @NotNull(message = "Vui lòng nhập địa chỉ")
        @Schema(description = "Địa chỉ của người dùng", example = "43 Phan Văn Trị, phường 2, quận 5, TP Hồ Chí Minh")
        String address,
        @NotNull(message = "Vui lòng nhập CCCD")
        @Schema(description = "Số CCCD của người dùng", example = "079097015437")
        String identification
) {
}

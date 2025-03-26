package com.paw.fund.app.modules.account_management.controller.models.verification.code;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record EmailVerificationCodeRequest(
        @Schema(description = "Mã xác thực người dùng nhập vào")
        @NotNull(message = "Vui long nhập mã xác thực")
        @Pattern(regexp = "^[0-9]{6}$", message = "Mã xác nhận không hợp lệ")
        String verificationCode
) {
}

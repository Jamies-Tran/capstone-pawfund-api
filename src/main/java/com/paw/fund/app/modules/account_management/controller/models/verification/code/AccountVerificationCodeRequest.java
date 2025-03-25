package com.paw.fund.app.modules.account_management.controller.models.verification.code;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record AccountVerificationCodeRequest(
        @Schema(description = "Email của người dùng", example = "nguyenvanbe@gmail.com")
        @NotNull(message = "Vui lập nhập email")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email không hợp lệ")
        String email,

        @Schema(description = "Mã xác thực người dùng nhập vào")
        @NotNull(message = "Vui long nhập mã xác thực")
        @Pattern(regexp = "^[0-9]{6}$", message = "Mã xác nhận không hợp lệ")
        String verificationCode
) {
    public static AccountVerificationCodeRequest of(String verificationCode) {
        return AccountVerificationCodeRequest.builder()
                .verificationCode(verificationCode)
                .build();
    }
}

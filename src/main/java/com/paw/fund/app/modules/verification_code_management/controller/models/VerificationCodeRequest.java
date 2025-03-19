package com.paw.fund.app.modules.verification_code_management.controller.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record VerificationCodeRequest(
        @NotNull(message = "Vui lập nhập email")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email không hợp lệ")
        String email
) {
}

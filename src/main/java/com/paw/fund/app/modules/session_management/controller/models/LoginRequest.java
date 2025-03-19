package com.paw.fund.app.modules.session_management.controller.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LoginRequest(
        @Schema(description = "Email đăng nhập", example = "admin@gmail.com")
        @NotNull(message = "Vui lòng nhập email")
        String email,
        @Schema(description = "Password đăng nhập", example = "admin")
        @NotNull(message = "Vui lòng nhập password")
        String password
) {
}

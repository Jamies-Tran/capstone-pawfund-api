package com.paw.fund.app.modules.login_info_management.controller.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record LoginRequest(
        @NotNull(message = "Vui lòng nhập email")
        String email,

        @NotNull(message = "Vui lòng nhập password")
        String password,

        BigDecimal latitude,

        BigDecimal longitude
) {
}

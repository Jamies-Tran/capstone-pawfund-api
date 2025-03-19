package com.paw.fund.app.modules.account_management.controller.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AccountUpdatePasswordRequest(
        @Schema(description = "Mật khẩu mới")
        @NotNull(message = "Vui lòng nhập mật khẩu mới")
        String password
) {
}

package com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record LoginInfo(
        String email,
        String password,
        BigDecimal longitude,
        BigDecimal latitude
) {
}

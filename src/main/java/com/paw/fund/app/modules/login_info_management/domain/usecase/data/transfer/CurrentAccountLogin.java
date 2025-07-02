package com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer;

import com.paw.fund.app.modules.account_management.domain.role.Role;
import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CurrentAccountLogin(
        Long accountId,
        String email,
        String phone,
        String fullName,
        BigDecimal latitude,
        BigDecimal longitude,
        @With List<Role> roles
) {
}

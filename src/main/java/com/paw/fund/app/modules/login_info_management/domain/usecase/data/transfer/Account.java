package com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer;

import com.paw.fund.app.modules.account_management.domain.role.Role;
import lombok.With;

import java.math.BigDecimal;
import java.util.List;

public record Account(
        Long accountId,
        String email,
        String password,
        String firstName,
        String lastName,
        @With BigDecimal latitude,
        @With BigDecimal longitude,
        String statusCode,
        String statusName,
        @With List<Role> roles
) {
}

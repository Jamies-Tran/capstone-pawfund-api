package com.paw.fund.app.modules.account_management.domain.role.usecase.data.transfer;

import lombok.Builder;

@Builder
public record RoleCode(String value) {
    public static RoleCode of(String roleCode) {
        return RoleCode.builder().value(roleCode).build();
    }
}

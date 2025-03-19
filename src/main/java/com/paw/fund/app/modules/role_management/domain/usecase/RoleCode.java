package com.paw.fund.app.modules.role_management.domain.usecase;

import lombok.Builder;

@Builder
public record RoleCode(String value) {
    public static RoleCode of(String roleCode) {
        return RoleCode.builder().value(roleCode).build();
    }
}

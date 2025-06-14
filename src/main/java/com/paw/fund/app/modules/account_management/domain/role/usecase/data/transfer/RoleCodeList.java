package com.paw.fund.app.modules.account_management.domain.role.usecase.data.transfer;

import lombok.Builder;

import java.util.List;

@Builder
public record RoleCodeList(List<String> value) {
    public static RoleCodeList of(List<String> roleCodes) {
        return RoleCodeList.builder()
                .value(roleCodes)
                .build();
    }
}

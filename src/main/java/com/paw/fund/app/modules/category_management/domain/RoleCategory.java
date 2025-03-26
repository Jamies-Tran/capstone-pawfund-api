package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.ERole;
import lombok.Builder;

@Builder
public record RoleCategory(
        String code,
        String name
) {
    public static RoleCategory of(ERole role) {
        return RoleCategory.builder()
                .code(role.getCode())
                .name(role.getName())
                .build();
    }
}

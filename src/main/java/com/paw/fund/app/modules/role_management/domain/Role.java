package com.paw.fund.app.modules.role_management.domain;

import lombok.Builder;

@Builder
public record Role(
        Long roleId,
        String roleCode,
        String roleName
) {
}

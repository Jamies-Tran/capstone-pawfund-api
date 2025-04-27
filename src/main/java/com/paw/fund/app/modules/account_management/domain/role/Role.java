package com.paw.fund.app.modules.account_management.domain.role;

import lombok.Builder;

@Builder
public record Role(
        Long roleId,
        String roleCode,
        String roleName
) {
}

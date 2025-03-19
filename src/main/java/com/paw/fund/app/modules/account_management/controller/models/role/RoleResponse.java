package com.paw.fund.app.modules.account_management.controller.models.role;

import lombok.Builder;

@Builder
public record RoleResponse(
        Long roleId,
        String roleCode,
        String roleName
) {
}

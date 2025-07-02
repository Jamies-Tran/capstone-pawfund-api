package com.paw.fund.app.modules.account_management.controller.models.role;

import com.paw.fund.common.category.CategoryResponse;
import lombok.Builder;

@Builder
public record RoleResponse(
        Long roleId,
        CategoryResponse role
) {
}

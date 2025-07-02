package com.paw.fund.app.modules.login_info_management.controller.models;

import com.paw.fund.common.category.CategoryResponse;

public record RoleResponse(
        Long roleId,
        CategoryResponse role
) {
}

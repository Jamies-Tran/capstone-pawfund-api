package com.paw.fund.app.modules.login_info_management.controller.models;

import com.paw.fund.app.modules.account_management.controller.models.medias.CommonMediaResponse;
import com.paw.fund.app.modules.account_management.domain.role.Role;
import com.paw.fund.common.category.CategoryResponse;
import com.paw.fund.common.category.GeometryResponse;
import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record AccountResponse(
        Long accountId,
        String email,
        String firstName,
        String lastName,
        CategoryResponse status,
        List<RoleResponse> roles
) {
}

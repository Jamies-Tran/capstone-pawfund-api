package com.paw.fund.app.modules.account_management.controller.models;

import com.paw.fund.app.modules.account_management.controller.models.medias.CommonMediaResponse;
import com.paw.fund.app.modules.account_management.controller.models.role.RoleResponse;
import com.paw.fund.common.category.CategoryResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record AccountResponse(
        Long accountId,
        String firstName,
        String lastName,
        String identification,

        String email,
        String phone,
        String address,
        LocalDate dateOfBirth,
        CategoryResponse gender,


        String thumbnail,
        List<RoleResponse> roles,
        List<CommonMediaResponse> medias,
        CategoryResponse status
) {
}

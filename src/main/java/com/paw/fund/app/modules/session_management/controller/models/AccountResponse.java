package com.paw.fund.app.modules.session_management.controller.models;

import com.paw.fund.app.modules.account_management.controller.models.medias.CommonMediaResponse;
import com.paw.fund.app.modules.account_management.controller.models.role.RoleResponse;
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
        String password,
        String phone,
        String address,
        LocalDate dateOfBirth,
        String genderCode,
        String genderName,
        List<RoleResponse> roles,
        String statusCode,
        String statusName,
        List<CommonMediaResponse> medias
) {
}

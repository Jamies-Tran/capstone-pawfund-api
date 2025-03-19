package com.paw.fund.app.modules.account_management.domain;

import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.role_management.domain.Role;
import lombok.Builder;
import lombok.With;

import java.time.LocalDate;
import java.util.List;

@Builder
public record Account(
        @With Long accountId,
        String firstName,
        String lastName,
        String identification,
        @With String email,
        String password,
        String phone,
        String address,
        LocalDate dateOfBirth,
        String genderCode,
        String genderName,
        @With List<Role> roles,
        @With List<CommonMedia> medias,
        String statusCode,
        String statusName
) {
}

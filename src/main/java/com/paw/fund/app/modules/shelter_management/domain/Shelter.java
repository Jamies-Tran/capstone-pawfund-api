package com.paw.fund.app.modules.shelter_management.domain;

import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.shelter_management.domain.registration.ShelterRegistration;
import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record Shelter(
        Long shelterId,
        Long accountRoleId,
        String shelterCode,
        String shelterName,
        String description,
        LocalDateTime dateOfPub,
        String email,
        String hotline,
        @With BigDecimal distance,
        @With List<CommonMedia> medias,
        @With String address,
        @With String ward,
        @With String district,
        @With String province,
        @With BigDecimal latitude,
        @With BigDecimal longitude,
        @With ShelterRegistration shelterRegistration,
        @With String statusCode,
        @With String statusName
) {
}

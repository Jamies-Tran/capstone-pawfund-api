package com.paw.fund.app.modules.shelter_management.controller.api.models;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ShelterResponse(
        Long shelterId,
        Long accountRoleId,
        String shelterCode,
        String shelterName,
        String description,
        LocalDateTime dateOfPub,
        String email,
        String hotline,
        String address,
        String ward,
        String district,
        String province,
        BigDecimal latitude,
        BigDecimal longitude,
        String statusCode,
        String statusName,
        List<ShelterMediaResponse> medias
) {
}

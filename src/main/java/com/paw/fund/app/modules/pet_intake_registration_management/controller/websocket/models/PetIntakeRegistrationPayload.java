package com.paw.fund.app.modules.pet_intake_registration_management.controller.websocket.models;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Builder
public record PetIntakeRegistrationPayload(
        String search,
        List<LocalDateTime> timeRange,
        List<String> petTypeCodes,
        List<String> reasonTypeCodes,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public PetIntakeRegistrationPayload {
        timeRange = Optional.ofNullable(timeRange).orElse(List.of());
        petTypeCodes = Optional.ofNullable(petTypeCodes).orElse(List.of());
        reasonTypeCodes = Optional.ofNullable(reasonTypeCodes).orElse(List.of());

        sorter = Optional.ofNullable(sorter).orElse("updatedAt");
        current = Optional.ofNullable(current).orElse(0);
        pageSize = Optional.ofNullable(pageSize).orElse(25);
    }
}

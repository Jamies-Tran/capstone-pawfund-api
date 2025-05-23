package com.paw.fund.app.modules.shelter_assignment_management.controller.websocket.models;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ShelterAssignmentSearchCriteriaPayload(
        Long petIntakeRegistrationId,
        List<LocalDateTime> timeRange,
        List<String> statusCodes,
        String sorter,
        Integer current,
        Integer pageSize
) {
}

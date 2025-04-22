package com.paw.fund.app.modules.shelter_management.controller.websocket.models;

import com.paw.fund.utils.validation.ValidationUtil;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record RequestAtTimeRangeMessage(
        List<LocalDateTime> requestAtTimeRange
) {
    public RequestAtTimeRangeMessage {
        requestAtTimeRange = ValidationUtil.validateNotNullOrDefaultTimeRange(requestAtTimeRange);
    }
}

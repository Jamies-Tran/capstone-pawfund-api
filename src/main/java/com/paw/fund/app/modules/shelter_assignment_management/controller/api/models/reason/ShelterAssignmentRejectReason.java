package com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.reason;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ShelterAssignmentRejectReason(
        @NotNull(message = "Lý do không tiếp nhận không được bỏ trống")
        String reason
) {
}

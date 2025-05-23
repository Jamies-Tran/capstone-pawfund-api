package com.paw.fund.app.modules.shelter_assignment_management.controller.api.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record ShelterAssignmentListRequest(
        @NotNull(message = "Thông tin trung tâm cứu trợ không được bỏ trống") @Size(min = 1, message = "Phải có ít nhất 1 trung tâm cứu trợ")
        List<Long> shelterIds
) {
}

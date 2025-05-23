package com.paw.fund.app.modules.category_management.controller.shelter.assignment.status.models;

import lombok.Builder;

@Builder
public record ShelterAssignmentStatusCategoryResponse(
        String code,
        String name
) {
}

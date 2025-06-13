package com.paw.fund.app.modules.category_management.controller.pet.color.models;

import lombok.Builder;

@Builder
public record PetColorCategoryResponse(
        String code,
        String name
) {
}

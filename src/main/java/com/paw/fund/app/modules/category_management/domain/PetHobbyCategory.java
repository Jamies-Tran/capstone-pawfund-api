package com.paw.fund.app.modules.category_management.domain;

import lombok.Builder;

@Builder
public record PetHobbyCategory(
        String code,
        String name
) {
}

package com.paw.fund.app.modules.pet_management.controller.hobby.models;

import lombok.Builder;

@Builder
public record HobbyRequest(
        String hobbyCode,
        String hobbyName
) {
}

package com.paw.fund.app.modules.pet_management.controller.hobby.models;

import lombok.Builder;

@Builder
public record HobbyResponse(
        Long hobbyId,
        String hobbyCode,
        String hobbyName,
        String statusCode,
        String statusName
) {
}

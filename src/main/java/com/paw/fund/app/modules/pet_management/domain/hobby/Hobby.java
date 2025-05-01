package com.paw.fund.app.modules.pet_management.domain.hobby;

import lombok.Builder;

@Builder
public record Hobby(
        Long hobbyId,
        String hobbyCode,
        String hobbyName,
        String statusCode,
        String statusName
) {
}

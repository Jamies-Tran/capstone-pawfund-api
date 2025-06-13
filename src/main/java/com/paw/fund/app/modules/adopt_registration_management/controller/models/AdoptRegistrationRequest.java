package com.paw.fund.app.modules.adopt_registration_management.controller.models;

import lombok.Builder;

@Builder
public record AdoptRegistrationRequest(
        Long petId,
        Long accountRoleId,
        Long formReplyId
) {
}

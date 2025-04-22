package com.paw.fund.app.modules.shelter_management.controller.api.registration.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ShelterRegistrationRejectRequest(
        @NotNull(message = "Vui lòng nhập lý do") String rejectReason) {
}

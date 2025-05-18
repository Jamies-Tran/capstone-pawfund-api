package com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PetIntakeRegistrationCancelReason(
        @NotNull(message = "Vui lòng nhập lý do hủy")
        String cancelReason
) {
}

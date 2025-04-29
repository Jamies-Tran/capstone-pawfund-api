package com.paw.fund.app.modules.pet_management.controller.type.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PetTypeRequest(
        @NotNull(message = "Mã loại thú cưng không được bỏ trống")
        String petTypeCode,

        @NotNull(message = "Tên loại thú cưng không được bỏ trống")
        String petTypeName
) {
}

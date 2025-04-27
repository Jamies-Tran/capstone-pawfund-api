package com.paw.fund.app.modules.pet_management.controller.breed.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PetBreedRequest(
        @NotNull(message = "Mã giống thú cưng không được bỏ trống")
        String breedCode,

        @NotNull(message = "Tên giống thú cưng không được bỏ trống")
        String breedName
) {
}

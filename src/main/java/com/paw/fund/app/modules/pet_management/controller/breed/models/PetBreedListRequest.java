package com.paw.fund.app.modules.pet_management.controller.breed.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record PetBreedListRequest(
        @Valid @Size(min = 1, message = "Yêu cầu có ít nhất một giống thú cưng")
        List<PetBreedRequest> list
) {
}

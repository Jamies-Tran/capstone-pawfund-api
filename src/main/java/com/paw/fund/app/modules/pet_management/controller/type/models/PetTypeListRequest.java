package com.paw.fund.app.modules.pet_management.controller.type.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record PetTypeListRequest(
        @Valid @Size(min = 1, message = "Yêu cầu có ít nhất một loại thú cưng")
        List<PetTypeRequest> list
) {
}

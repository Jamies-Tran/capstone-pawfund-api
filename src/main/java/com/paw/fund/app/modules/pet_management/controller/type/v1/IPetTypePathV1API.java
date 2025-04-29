package com.paw.fund.app.modules.pet_management.controller.type.v1;

import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeRequest;
import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/pet/type/{petTypeId}")
@Tag(name = "Pet Type V1", description = "QL loại thú cưng")
public interface IPetTypePathV1API {
    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ValueResponse<PetTypeResponse> updatePetType(
            @PathVariable
            Long petTypeId,

            @Valid @RequestBody
            PetTypeRequest request);

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ValueResponse<?> deletePetType(@PathVariable Long petTypeId);
}

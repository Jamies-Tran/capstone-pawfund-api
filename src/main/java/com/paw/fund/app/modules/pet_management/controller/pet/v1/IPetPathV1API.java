package com.paw.fund.app.modules.pet_management.controller.pet.v1;

import com.paw.fund.app.modules.pet_management.controller.pet.models.PetRequest;
import com.paw.fund.app.modules.pet_management.controller.pet.models.PetResponse;
import com.paw.fund.app.modules.pet_management.controller.pet.models.PetUpdateRequest;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/pet/{petId}")
@Tag(name = "PET V1", description = "QL thú cưng")
public interface IPetPathV1API {
    @PutMapping
    @PreAuthorize("hasAnyRole({'ROLE_SHELTER_OWNER', 'ROLE_STAFF'})")
    ValueResponse<PetResponse> updatePet(
            @PathVariable
            Long petId,

            @Valid @RequestBody
            PetUpdateRequest request);

    @DeleteMapping
    @PreAuthorize("hasAnyRole({'ROLE_SHELTER_OWNER', 'ROLE_STAFF'})")
    ValueResponse<?> deletePet(@PathVariable Long petId);
}

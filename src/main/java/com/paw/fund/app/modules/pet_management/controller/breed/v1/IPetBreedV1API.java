package com.paw.fund.app.modules.pet_management.controller.breed.v1;

import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedRequest;
import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/pet/breed")
@Tag(name = "Pet Breed V1", description = "QL giống thú cưng")
public interface IPetBreedV1API {

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ValueResponse<PetBreedResponse> createPetBreed(
            @RequestBody @Valid
            PetBreedRequest request);
}

package com.paw.fund.app.modules.pet_management.controller.breed.v1;

import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedRequest;
import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/pet/breed/{petBreedId}")
@Tag(name = "Pet Breed V1", description = "QL giống thú cưng")
public interface IPetBreedPathV1API {
    @PutMapping
    ValueResponse<PetBreedResponse> updatePetBreed(
            @PathVariable
            Long petBreedId,

            @RequestBody @Valid
            PetBreedRequest request);
}

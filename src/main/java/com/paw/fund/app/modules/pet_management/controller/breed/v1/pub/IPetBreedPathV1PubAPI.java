package com.paw.fund.app.modules.pet_management.controller.breed.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/public/pet/breed/{petBreedId}")
@Tag(name = "Pet Breed V1", description = "QL giống thú cưng")
public interface IPetBreedPathV1PubAPI {
    @GetMapping
    ValueResponse<PetBreedResponse> getPetBreedDetail(@PathVariable Long petBreedId);
}

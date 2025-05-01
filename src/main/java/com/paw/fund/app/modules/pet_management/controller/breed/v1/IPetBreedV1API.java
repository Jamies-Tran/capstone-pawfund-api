package com.paw.fund.app.modules.pet_management.controller.breed.v1;

import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedListRequest;
import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedRequest;
import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedResponse;
import com.paw.fund.utils.response.ListResponse;
import com.paw.fund.utils.response.PageResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/v1/api/pet/breed")
@Tag(name = "Pet Breed V1", description = "QL giống thú cưng")
public interface IPetBreedV1API {

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ValueResponse<PetBreedResponse> createPetBreed(
            @RequestBody @Valid
            PetBreedRequest request);

    @PostMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ListResponse<PetBreedResponse> createPetBreedList(
            @RequestBody @Valid
            PetBreedListRequest request);

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    PageResponse<PetBreedResponse> getPetBreedList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,

            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );
}

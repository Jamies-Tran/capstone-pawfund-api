package com.paw.fund.app.modules.pet_management.controller.breed.v1;

import com.paw.fund.app.modules.pet_management.controller.breed.models.IPetBreedModelMapper;
import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedRequest;
import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedResponse;
import com.paw.fund.app.modules.pet_management.domain.breed.PetBreed;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedId;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedUpdate;
import com.paw.fund.app.modules.pet_management.service.breed.usecase.IPetBreedUseCase;
import com.paw.fund.utils.response.ValueResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetBreedPathV1Controller implements IPetBreedPathV1API {
    IPetBreedUseCase useCase;

    IPetBreedModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<PetBreedResponse> updatePetBreed(Long petBreedId, PetBreedRequest request) {
        PetBreed petBreed = useCase.updatePetBreed(PetBreedUpdate.of(petBreedId, modelMapper.toDto(request)));

        return ValueResponse.success(modelMapper.toResponse(petBreed), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<?> deletePetBreed(Long petBreedId) {
        useCase.deletePetBreed(PetBreedId.of(petBreedId));

        return ValueResponse.success(null, HttpStatus.NO_CONTENT, API_VERSION);
    }
}

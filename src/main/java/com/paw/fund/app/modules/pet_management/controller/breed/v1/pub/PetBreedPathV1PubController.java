package com.paw.fund.app.modules.pet_management.controller.breed.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.breed.models.IPetBreedModelMapper;
import com.paw.fund.app.modules.pet_management.controller.breed.models.PetBreedResponse;
import com.paw.fund.app.modules.pet_management.domain.breed.PetBreed;
import com.paw.fund.app.modules.pet_management.domain.breed.usecase.PetBreedId;
import com.paw.fund.app.modules.pet_management.service.breed.usecase.IPetBreedUseCase;
import com.paw.fund.utils.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetBreedPathV1PubController implements IPetBreedPathV1PubAPI {
    @NonNull
    IPetBreedUseCase useCase;

    @NonNull
    IPetBreedModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<PetBreedResponse> getPetBreedDetail(Long petBreedId) {
        PetBreed petBreed = useCase.getPetBreedDetail(PetBreedId.of(petBreedId));

        return ValueResponse.success(modelMapper.toResponse(petBreed), HttpStatus.OK, API_VERSION);
    }
}

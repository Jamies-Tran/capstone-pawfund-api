package com.paw.fund.app.modules.pet_management.controller.pet.v1;

import com.paw.fund.app.modules.pet_management.controller.pet.models.IPetModelMapper;
import com.paw.fund.app.modules.pet_management.controller.pet.models.PetRequest;
import com.paw.fund.app.modules.pet_management.controller.pet.models.PetResponse;
import com.paw.fund.app.modules.pet_management.controller.pet.models.PetUpdateRequest;
import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetId;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetUpdate;
import com.paw.fund.app.modules.pet_management.service.pet.usecase.IPetUseCase;
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
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class PetPathV1Controller implements IPetPathV1API {
    @NonNull
    IPetUseCase useCase;
    
    @NonNull
    IPetModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<PetResponse> updatePet(Long petId, PetUpdateRequest request) {
        Pet pet = useCase.updatePet(PetUpdate.of(petId, modelMapper.toDto(request)));
        
        return ValueResponse.success(modelMapper.toResponse(pet), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<?> deletePet(Long petId) {
        useCase.deletePet(PetId.of(petId));

        return ValueResponse.success(null, HttpStatus.NO_CONTENT, API_VERSION);
    }
}

package com.paw.fund.app.modules.pet_management.controller.pet.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.pet.models.IPetModelMapper;
import com.paw.fund.app.modules.pet_management.controller.pet.models.PetResponse;
import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import com.paw.fund.app.modules.pet_management.domain.pet.usecase.PetId;
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
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetPathV1PubController implements IPetPathV1PubAPI {
    @NonNull
    IPetUseCase useCase;

    @NonNull
    IPetModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<PetResponse> getPetDetail(Long petId) {
        Pet pet = useCase.getPetDetail(PetId.of(petId));

        return ValueResponse.success(modelMapper.toResponse(pet), HttpStatus.OK);
    }
}

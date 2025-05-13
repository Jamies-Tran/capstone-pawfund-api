package com.paw.fund.app.modules.pet_intake_registration_management.controller.api.v2.pub;

import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.IPetIntakeRegistrationModelMapper;
import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.PetIntakeRegistrationRequest;
import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.PetIntakeRegistrationResponse;
import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.PetIntakeRegistrationUpdateRequest;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationDelete;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationUpdate;
import com.paw.fund.app.modules.pet_intake_registration_management.service.usecase.IPetIntakeRegistrationUseCase;
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
public class PetIntakeRegistrationPathV2PubController implements IPetIntakeRegistrationPathV2PubAPI {
    @NonNull
    IPetIntakeRegistrationUseCase useCase;

    @NonNull
    IPetIntakeRegistrationModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<PetIntakeRegistrationResponse> updatePetIntakeRegistration(Long petIntakeRegistrationId, PetIntakeRegistrationUpdateRequest request) {
        PetIntakeRegistration petIntakeRegistration = modelMapper.toDto(request);
        PetIntakeRegistration updatePetIntakeRegistration = useCase
                .updatePetIntakeRegistration(PetIntakeRegistrationUpdate.of(petIntakeRegistrationId, petIntakeRegistration));

        return ValueResponse.success(modelMapper.toResponse(updatePetIntakeRegistration), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<?> deletePetIntakeRegistration(Long petIntakeRegistrationId, String phone) {
        useCase.delete(PetIntakeRegistrationDelete.of(petIntakeRegistrationId, phone));

        return ValueResponse.success(null, HttpStatus.NO_CONTENT, API_VERSION);
    }
}

package com.paw.fund.app.modules.pet_intake_registration_management.controller.api.v1.pub;

import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.IPetIntakeRegistrationModelMapper;
import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.PetIntakeRegistrationResponse;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase.PetIntakeRegistrationId;
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
public class PetIntakeRegistrationPathV1PubController implements IPetIntakeRegistrationPathV1PubAPI {
    @NonNull
    IPetIntakeRegistrationUseCase useCase;

    @NonNull
    IPetIntakeRegistrationModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<PetIntakeRegistrationResponse> getPetIntakeRegistrationDetail(Long petIntakeRegistrationId) {
        PetIntakeRegistration petIntakeRegistration = useCase.getPetIntakeRegistrationDetail(
                PetIntakeRegistrationId.of(petIntakeRegistrationId));

        return ValueResponse.success(modelMapper.toResponse(petIntakeRegistration), HttpStatus.OK, API_VERSION);
    }
}

package com.paw.fund.app.modules.shelter_management.controller.api.registration;

import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.IShelterRegistrationModelMapper;
import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterRegistrationRequest;
import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterResponse;
import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationCreate;
import com.paw.fund.app.modules.shelter_management.service.usecase.IShelterUseCase;
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
public class ShelterRegistrationV1Controller implements IShelterRegistrationV1API {
    @NonNull
    IShelterUseCase useCase;

    @NonNull
    IShelterRegistrationModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<ShelterResponse> registerShelter(ShelterRegistrationRequest request) {
        Shelter shelter = modelMapper.toDto(request);
        Shelter saveShelter = useCase
                .registerShelter(ShelterRegistrationCreate
                        .of(request.placeId(), request.formResponseId(), shelter));

        return ValueResponse.success(
                modelMapper.toResponse(saveShelter),
                HttpStatus.CREATED,
                API_VERSION);
    }
}

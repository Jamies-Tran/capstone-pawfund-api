package com.paw.fund.app.modules.shelter_management.controller.api.registration;

import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.IShelterRegistrationModelMapper;
import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterRegistrationRejectRequest;
import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterRegistrationResponse;
import com.paw.fund.app.modules.shelter_management.domain.registration.ShelterRegistration;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationId;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationReject;
import com.paw.fund.app.modules.shelter_management.service.usecase.IShelterRegistrationUseCase;
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
public class ShelterRegistrationPathV1Controller implements IShelterRegisterPathV1API {
    @NonNull
    IShelterRegistrationUseCase useCase;

    @NonNull
    IShelterRegistrationModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<ShelterRegistrationResponse> receiveShelter(Long shelterRegistrationId) {
        ShelterRegistration shelterRegistration = useCase
                .receiveShelterRegistration(ShelterRegistrationId.of(shelterRegistrationId));

        return ValueResponse.success(modelMapper.toResponse(shelterRegistration), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<ShelterRegistrationResponse> approveShelter(Long shelterRegistrationId) {
        ShelterRegistration shelterRegistration = useCase
                .approveShelterRegistration(ShelterRegistrationId.of(shelterRegistrationId));

        return ValueResponse.success(modelMapper.toResponse(shelterRegistration), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<ShelterRegistrationResponse> rejectShelter(Long shelterRegistrationId, ShelterRegistrationRejectRequest request) {
        ShelterRegistrationReject registrationReject = ShelterRegistrationReject
                .of(shelterRegistrationId, request.rejectReason());
        ShelterRegistration shelterRegistration = useCase
                .rejectedShelterRegistration(registrationReject);

        return ValueResponse.success(modelMapper.toResponse(shelterRegistration), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<ShelterRegistrationResponse> getShelterRegistrationDetail(Long shelterRegistrationId) {
        ShelterRegistration shelterRegistration = useCase
                .getShelterRegistrationDetail(ShelterRegistrationId.of(shelterRegistrationId));

        return ValueResponse.success(modelMapper.toResponse(shelterRegistration), HttpStatus.OK, API_VERSION);
    }
}

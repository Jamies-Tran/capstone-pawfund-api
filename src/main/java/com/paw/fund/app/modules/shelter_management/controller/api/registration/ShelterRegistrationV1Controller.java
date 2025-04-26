package com.paw.fund.app.modules.shelter_management.controller.api.registration;

import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.IShelterRegistrationModelMapper;
import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterRegistrationRequest;
import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterRegistrationResponse;
import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterResponse;
import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationCreate;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationFilter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationSearchCriteria;
import com.paw.fund.app.modules.shelter_management.service.usecase.IShelterRegistrationUseCase;
import com.paw.fund.app.modules.shelter_management.service.usecase.IShelterUseCase;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.response.Meta;
import com.paw.fund.utils.response.PageResponse;
import com.paw.fund.utils.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterRegistrationV1Controller implements IShelterRegistrationV1API {
    @NonNull
    IShelterUseCase useCase;

    @NonNull
    IShelterRegistrationUseCase registrationUseCase;

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

    @Override
    public PageResponse<ShelterRegistrationResponse> getShelterRegistrationProcessingByAccount (List<LocalDateTime> requestAtTimeRange,
                                                                                                List<LocalDateTime> receivedAtTimeRange,
                                                                                                List<LocalDateTime> approvedAtTimeRange,
                                                                                                List<LocalDateTime> rejectedAtTimeRange,
                                                                                                List<String> statusCodes,
                                                                                                Integer current, Integer pageSize) {
        ShelterRegistrationSearchCriteria searchCriteria = ShelterRegistrationSearchCriteria
                .of(requestAtTimeRange, receivedAtTimeRange, approvedAtTimeRange, rejectedAtTimeRange, statusCodes);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize);
        Page<ShelterRegistrationResponse> responses = registrationUseCase
                .getShelterRegistrationListProcessingByAccount(ShelterRegistrationFilter.of(searchCriteria, pageRequestCustom))
                .map(modelMapper::toResponse);

        return PageResponse.success(responses.getContent(), Meta.of(responses), HttpStatus.OK, API_VERSION);
    }
}

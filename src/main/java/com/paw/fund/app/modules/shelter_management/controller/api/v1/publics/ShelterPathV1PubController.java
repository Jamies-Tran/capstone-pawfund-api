package com.paw.fund.app.modules.shelter_management.controller.api.v1.publics;

import com.paw.fund.app.modules.shelter_management.controller.api.models.IShelterModelMapper;
import com.paw.fund.app.modules.shelter_management.controller.api.models.ShelterResponse;
import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterId;
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
public class ShelterPathV1PubController implements IShelterPathV1PubAPI {
    @NonNull
    IShelterUseCase useCase;

    @NonNull
    IShelterModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<ShelterResponse> getShelterId(Long shelterId) {
        Shelter shelter = useCase.getShelterDetail(ShelterId.of(shelterId));

        return ValueResponse.success(modelMapper.toResponse(shelter), HttpStatus.OK, API_VERSION);
    }
}

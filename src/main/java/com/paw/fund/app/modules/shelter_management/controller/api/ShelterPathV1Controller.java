package com.paw.fund.app.modules.shelter_management.controller.api;

import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.shelter_management.controller.api.models.IShelterModelMapper;
import com.paw.fund.app.modules.shelter_management.controller.api.models.ShelterActiveRequest;
import com.paw.fund.app.modules.shelter_management.controller.api.models.ShelterResponse;
import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterActive;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterPathV1Controller implements IShelterPathV1API {
    @NonNull
    IShelterUseCase useCase;

    @NonNull
    IShelterModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<ShelterResponse> activeShelter(Long shelterId, ShelterActiveRequest request) {
        List<CommonMedia> medias = request.medias().stream()
                .map(x -> CommonMedia.builder()
                        .url(x.url())
                        .isThumbnail(x.isThumbnail())
                        .build())
                .toList();
        ShelterActive shelterActive = ShelterActive.of(request.description(), shelterId, medias);
        Shelter shelter = useCase.activeShelter(shelterActive);

        return ValueResponse.success(modelMapper.toResponse(shelter), HttpStatus.OK, API_VERSION);
    }
}

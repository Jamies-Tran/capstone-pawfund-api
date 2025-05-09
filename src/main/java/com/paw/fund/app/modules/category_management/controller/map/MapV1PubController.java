package com.paw.fund.app.modules.category_management.controller.map;

import com.paw.fund.app.modules.category_management.controller.map.models.IMapModelMapper;
import com.paw.fund.app.modules.category_management.controller.map.models.PredictionResponse;
import com.paw.fund.app.modules.category_management.controller.map.models.PredictionsResponse;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.repository.feign.data.place.Prediction;
import com.paw.fund.app.modules.category_management.service.map.uscase.IMapUseCase;
import com.paw.fund.utils.response.ListResponse;
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
public class MapV1PubController implements IMapV1PubAPI {
    @NonNull
    IMapUseCase useCase;

    @NonNull
    IMapModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<PredictionsResponse> predictPlaceList(String search, Integer limit) {
        Prediction prediction = useCase.predictPlaceList(CategorySearch.of(search, limit));
        PredictionResponse response = modelMapper.toResponse(prediction);

        return ListResponse.success(response.predictions(), HttpStatus.OK, API_VERSION);
    }
}

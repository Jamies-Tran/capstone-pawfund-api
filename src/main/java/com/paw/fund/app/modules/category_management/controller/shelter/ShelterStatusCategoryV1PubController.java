package com.paw.fund.app.modules.category_management.controller.shelter;

import com.paw.fund.app.modules.category_management.controller.shelter.models.IShelterStatusModelMapper;
import com.paw.fund.app.modules.category_management.controller.shelter.models.ShelterStatusCategoryResponse;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.usecase.IShelterStatusCategoryUseCase;
import com.paw.fund.utils.response.ListResponse;
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
public class ShelterStatusCategoryV1PubController implements IShelterStatusCategoryV1PubAPI {
    @NonNull
    IShelterStatusCategoryUseCase useCase;

    @NonNull
    IShelterStatusModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<ShelterStatusCategoryResponse> getShelterStatusCategoryList(String search) {
        List<ShelterStatusCategoryResponse> responses = useCase.getShelterStatusCategoryList(CategorySearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.OK, API_VERSION);
    }
}

package com.paw.fund.app.modules.category_management.controller.gender;

import com.paw.fund.app.modules.category_management.controller.gender.models.GenderCategoryResponse;
import com.paw.fund.app.modules.category_management.controller.gender.models.IGenderCategoryModelMapper;
import com.paw.fund.app.modules.category_management.domain.usecase.GenderSearch;
import com.paw.fund.app.modules.category_management.service.gender.usecase.IGenderCategoryUseCase;
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
public class GenderCategoryV1PubController implements IGenderCategoryV1PubAPI {
    @NonNull
    IGenderCategoryUseCase useCase;

    @NonNull
    IGenderCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<GenderCategoryResponse> getGenderList(String search) {
        List<GenderCategoryResponse> responses = useCase.getGenderList(GenderSearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.OK, API_VERSION);
    }
}

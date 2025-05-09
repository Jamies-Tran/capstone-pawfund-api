package com.paw.fund.app.modules.category_management.controller.shelter.registration;

import com.paw.fund.app.modules.category_management.controller.shelter.models.IShelterRegistrationStatusCategoryModelMapper;
import com.paw.fund.app.modules.category_management.controller.shelter.models.ShelterRegistrationStatusCategoryResponse;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.shelter.registration.usecase.IShelterRegistrationStatusCategoryUseCase;
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
public class ShelterRegistrationStatusCategoryV1PubController implements IShelterRegistrationStatusRegistrationCategoryV1PubAPI {
    @NonNull
    IShelterRegistrationStatusCategoryUseCase useCase;

    @NonNull
    IShelterRegistrationStatusCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;


    @Override
    public ListResponse<ShelterRegistrationStatusCategoryResponse> getShelterRegistrationStatusCategoryList(String search) {
        List<ShelterRegistrationStatusCategoryResponse> responses = useCase.getShelterRegistrationCategoryList(CategorySearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.OK, API_VERSION);
    }
}

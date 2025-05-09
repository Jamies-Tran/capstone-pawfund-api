package com.paw.fund.app.modules.category_management.controller.pet.health.status;

import com.paw.fund.app.modules.category_management.controller.pet.health.status.models.IPetHealthStatusCategoryModelMapper;
import com.paw.fund.app.modules.category_management.controller.pet.health.status.models.PetHealthStatusCategoryResponse;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.pet.health.status.usecase.IPetHealthStatusCategoryUseCase;
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
public class PetHealthStatusCategoryV1PubController implements IPetHealthStatusCategoryV1PubAPI {
    @NonNull
    IPetHealthStatusCategoryUseCase useCase;

    @NonNull
    IPetHealthStatusCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<PetHealthStatusCategoryResponse> getPetHealthStatusCategoryList(String search) {
        List<PetHealthStatusCategoryResponse> response = useCase.getPetHealthStatusCategoryList(CategorySearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(response, HttpStatus.OK, API_VERSION);
    }
}

package com.paw.fund.app.modules.category_management.controller.pet.status;

import com.paw.fund.app.modules.category_management.controller.pet.status.models.IPetStatusCategoryModelMapper;
import com.paw.fund.app.modules.category_management.controller.pet.status.models.PetStatusCategoryResponse;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.pet.status.usecase.IPetStatusCategoryUseCase;
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
public class PetStatusCategoryV1PubController implements IPetStatusCategoryV1PubAPI {
    @NonNull
    IPetStatusCategoryUseCase useCase;

    @NonNull
    IPetStatusCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<PetStatusCategoryResponse> getPetStatusList(String search) {
        List<PetStatusCategoryResponse> responses = useCase.getPetStatusCategoryList(CategorySearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.OK, API_VERSION);
    }
}

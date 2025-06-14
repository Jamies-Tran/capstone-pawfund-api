package com.paw.fund.app.modules.category_management.controller.pet.color;

import com.paw.fund.app.modules.category_management.controller.pet.color.models.IPetColorCategoryModelMapper;
import com.paw.fund.app.modules.category_management.controller.pet.color.models.PetColorCategoryResponse;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.pet.color.usecase.IPetColorCategoryUseCase;
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
public class PetColorCategoryV1PubController implements IPetColorCategoryV1PubAPI {
    @NonNull
    IPetColorCategoryUseCase useCase;

    @NonNull
    IPetColorCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<PetColorCategoryResponse> getPetColorCategoryList(String search) {
        List<PetColorCategoryResponse> responses = useCase.getPetColorCategoryList(CategorySearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.OK);
    }
}

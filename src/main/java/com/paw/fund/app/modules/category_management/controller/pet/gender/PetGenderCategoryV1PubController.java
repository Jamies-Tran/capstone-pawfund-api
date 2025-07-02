package com.paw.fund.app.modules.category_management.controller.pet.gender;

import com.paw.fund.app.modules.category_management.controller.pet.gender.models.IPetGenderCategoryModelMapper;
import com.paw.fund.app.modules.category_management.controller.pet.gender.models.PetGenderCategoryResponse;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.pet.gender.usecase.IPetGenderCategoryUseCase;
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
public class PetGenderCategoryV1PubController implements IPetGenderCategoryV1PubAPI {
    @NonNull
    IPetGenderCategoryUseCase useCase;

    @NonNull
    IPetGenderCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<PetGenderCategoryResponse> getPetGenderCategoryList(String search) {
        List<PetGenderCategoryResponse> responses = useCase.getPetGenderCategoryList(CategorySearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.OK);
    }
}

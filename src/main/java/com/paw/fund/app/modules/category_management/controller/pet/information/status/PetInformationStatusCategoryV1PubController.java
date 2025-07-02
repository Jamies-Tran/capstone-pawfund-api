package com.paw.fund.app.modules.category_management.controller.pet.information.status;

import com.paw.fund.app.modules.category_management.controller.pet.information.status.models.IPetInformationStatusCategoryModelMapper;
import com.paw.fund.app.modules.category_management.controller.pet.information.status.models.PetInformationStatusCategoryResponse;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.pet.information.status.usecase.IPetInformationStatusCategoryUseCase;
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
public class PetInformationStatusCategoryV1PubController implements IPetInformationStatusCategoryV1PubAPI {
    @NonNull
    IPetInformationStatusCategoryUseCase useCase;

    @NonNull
    IPetInformationStatusCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<PetInformationStatusCategoryResponse> getPetInformationStatusCategoryList(String search) {
        List<PetInformationStatusCategoryResponse> responses = useCase.getPetInformationCategoryList(CategorySearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.OK);
    }
}

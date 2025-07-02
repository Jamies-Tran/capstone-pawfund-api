package com.paw.fund.app.modules.category_management.controller.shelter.assignment.status;

import com.paw.fund.app.modules.category_management.controller.shelter.assignment.status.models.IShelterAssignmentStatusCategoryModelMapper;
import com.paw.fund.app.modules.category_management.controller.shelter.assignment.status.models.ShelterAssignmentStatusCategoryResponse;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.shelter.assignment.status.usecase.IShelterAssignmentStatusCategoryUseCase;
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
public class ShelterAssignmentStatusCategoryV1PubController implements IShelterAssignmentStatusCategoryV1PubAPI {
    @NonNull
    IShelterAssignmentStatusCategoryUseCase useCase;

    @NonNull
    IShelterAssignmentStatusCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<ShelterAssignmentStatusCategoryResponse> getShelterAssignmentStatusCategoryList(String search) {
        List<ShelterAssignmentStatusCategoryResponse> responses = useCase.getShelterAssignmentStatusCategoryList(CategorySearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.OK);
    }
}

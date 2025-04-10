package com.paw.fund.app.modules.category_management.controller.form.status;

import com.paw.fund.app.modules.category_management.controller.form.models.status.FormStatusCategoryResponse;
import com.paw.fund.app.modules.category_management.controller.form.models.status.IFormStatusCategoryModelMapper;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.usecase.IFormStatusCategoryUseCase;
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
public class FormStatusCategoryV1PubController implements IFormStatusCategoryV1PubAPI {
    @NonNull
    IFormStatusCategoryUseCase useCase;

    @NonNull
    IFormStatusCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<FormStatusCategoryResponse> findFormStatusList(String search) {
        List<FormStatusCategoryResponse> responses = useCase.findFormStatusList(CategorySearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.OK, API_VERSION);
    }
}

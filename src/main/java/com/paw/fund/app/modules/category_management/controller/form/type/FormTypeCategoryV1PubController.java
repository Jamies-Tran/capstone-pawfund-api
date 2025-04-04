package com.paw.fund.app.modules.category_management.controller.form.type;

import com.paw.fund.app.modules.category_management.controller.form.type.models.FormTypeCategoryResponse;
import com.paw.fund.app.modules.category_management.controller.form.type.models.IFormTypeCategoryModelMapper;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.usecase.IFormTypeCategoryUseCase;
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
public class FormTypeCategoryV1PubController implements IFormTypeCategoryV1PubAPI {
    @NonNull
    IFormTypeCategoryUseCase useCase;

    @NonNull
    IFormTypeCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<FormTypeCategoryResponse> getFormTypeList(String search) {
        List<FormTypeCategoryResponse> responses = useCase.getFormTypeList(CategorySearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.OK, API_VERSION);
    }
}

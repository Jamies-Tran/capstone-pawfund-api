package com.paw.fund.app.modules.category_management.controller.question.type;

import com.paw.fund.app.modules.category_management.controller.question.type.models.IQuestionTypeCategoryModelMapper;
import com.paw.fund.app.modules.category_management.controller.question.type.models.QuestionTypeCategoryResponse;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.usecase.IQuestionTypeCategoryUseCase;
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
public class QuestionTypeCategoryV1PubController implements IQuestionTypeCategoryV1PubAPI {
    @NonNull
    IQuestionTypeCategoryUseCase useCase;

    @NonNull
    IQuestionTypeCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<QuestionTypeCategoryResponse> getQuestionTypeList(String search) {
        List<QuestionTypeCategoryResponse> responses = useCase.getQuestionTypeList(CategorySearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(
                responses,
                HttpStatus.OK,
                API_VERSION);
    }
}

package com.paw.fund.app.modules.category_management.service.question.type;

import com.paw.fund.app.modules.category_management.domain.QuestionTypeCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.question.type.usecase.IQuestionTypeCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionTypeCategoryUseCaseService implements IQuestionTypeCategoryUseCase {
    @NonNull
    QuestionTypeCategoryQueryService queryService;

    @Override
    public List<QuestionTypeCategory> getQuestionTypeList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

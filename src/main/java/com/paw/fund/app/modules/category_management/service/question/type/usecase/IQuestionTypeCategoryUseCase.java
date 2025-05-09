package com.paw.fund.app.modules.category_management.service.question.type.usecase;

import com.paw.fund.app.modules.category_management.domain.QuestionTypeCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IQuestionTypeCategoryUseCase {
    List<QuestionTypeCategory> getQuestionTypeList(CategorySearch search);
}

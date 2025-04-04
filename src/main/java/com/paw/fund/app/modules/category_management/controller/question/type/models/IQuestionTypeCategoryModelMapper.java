package com.paw.fund.app.modules.category_management.controller.question.type.models;

import com.paw.fund.app.modules.category_management.domain.QuestionTypeCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IQuestionTypeCategoryModelMapper {
    QuestionTypeCategoryResponse toResponse(QuestionTypeCategory dto);
}

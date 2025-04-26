package com.paw.fund.app.modules.form_management.controller.form.v1.models.question;

import com.paw.fund.app.modules.form_management.controller.form.v1.models.option.IOptionModelMapper;
import com.paw.fund.app.modules.form_management.domain.question.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {IOptionModelMapper.class})
public interface IQuestionModelMapper {
    Question toDto(QuestionRequest request);

    Question toDto(QuestionUpdateRequest request);

    QuestionResponse toResponse(Question dto);
}

package com.paw.fund.app.modules.form_management.controller.models.question;

import com.paw.fund.app.modules.form_management.controller.models.option.IOptionModelMapper;
import com.paw.fund.app.modules.form_management.domain.question.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {IOptionModelMapper.class})
public interface IQuestionModelMapper {
    Question toDto(QuestionRequest request);

    QuestionResponse toResponse(Question dto);
}

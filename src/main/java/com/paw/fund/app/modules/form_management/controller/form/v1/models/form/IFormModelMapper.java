package com.paw.fund.app.modules.form_management.controller.form.v1.models.form;

import com.paw.fund.app.modules.form_management.controller.form.v1.models.question.IQuestionModelMapper;
import com.paw.fund.app.modules.form_management.domain.form.Form;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {IQuestionModelMapper.class})
public interface IFormModelMapper {
    Form toDto(FormRequest request);

    Form toDto(FormUpdateRequest request);

    FormResponse toResponse(Form dto);
}

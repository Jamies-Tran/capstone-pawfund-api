package com.paw.fund.app.modules.form_management.domain.question;

import com.paw.fund.app.modules.form_management.repository.database.question.QuestionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IQuestionMapper {
    QuestionEntity toEntity(Question dto);

    Question toDto(QuestionEntity entity);
}

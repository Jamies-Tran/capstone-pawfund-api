package com.paw.fund.app.modules.form_management.domain.answer;

import com.paw.fund.app.modules.form_management.repository.database.answer.AnswerDAO;
import com.paw.fund.app.modules.form_management.repository.database.answer.AnswerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IAnswerMapper {
    AnswerEntity toEntity(Answer dto);

    Answer toDto(AnswerEntity entity);

    Answer toDto(AnswerDAO dao);

    void update(@MappingTarget AnswerEntity entity, Answer dto);
}

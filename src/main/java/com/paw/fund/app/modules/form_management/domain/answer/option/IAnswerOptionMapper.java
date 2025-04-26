package com.paw.fund.app.modules.form_management.domain.answer.option;

import com.paw.fund.app.modules.form_management.repository.database.answer.option.AnswerOptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IAnswerOptionMapper {
    AnswerOptionEntity toEntity(AnswerOption dto);

    AnswerOption toDto(AnswerOptionEntity entity);

    void update(@MappingTarget AnswerOptionEntity entity, AnswerOption dto);
}

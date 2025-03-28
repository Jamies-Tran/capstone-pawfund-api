package com.paw.fund.app.modules.form_management.domain.option;

import com.paw.fund.app.modules.form_management.repository.database.option.OptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IOptionMapper {
    OptionEntity toEntity(Option option);

    Option toDto(OptionEntity entity);
}

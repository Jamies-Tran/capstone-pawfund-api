package com.paw.fund.app.modules.form_management.domain.form;

import com.paw.fund.app.modules.form_management.repository.database.form.FormEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IFormMapper {
    FormEntity toEntity(Form dto);

    Form toDto(FormEntity entity);
}

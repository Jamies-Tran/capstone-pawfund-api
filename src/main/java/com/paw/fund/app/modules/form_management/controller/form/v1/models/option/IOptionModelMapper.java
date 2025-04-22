package com.paw.fund.app.modules.form_management.controller.form.v1.models.option;

import com.paw.fund.app.modules.form_management.domain.option.Option;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IOptionModelMapper {
    Option toDto(OptionRequest request);

    Option toDto(OptionUpdateRequest request);

    OptionResponse toResponse(Option dto);
}

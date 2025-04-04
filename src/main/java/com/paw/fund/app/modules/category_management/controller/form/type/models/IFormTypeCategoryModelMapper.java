package com.paw.fund.app.modules.category_management.controller.form.type.models;

import com.paw.fund.app.modules.category_management.domain.FormTypeCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IFormTypeCategoryModelMapper {
    FormTypeCategoryResponse toResponse(FormTypeCategory dto);
}

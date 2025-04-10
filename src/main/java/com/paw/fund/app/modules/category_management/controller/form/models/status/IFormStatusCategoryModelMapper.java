package com.paw.fund.app.modules.category_management.controller.form.models.status;

import com.paw.fund.app.modules.category_management.domain.FormStatusCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IFormStatusCategoryModelMapper {
    FormStatusCategoryResponse toResponse(FormStatusCategory dto);
}

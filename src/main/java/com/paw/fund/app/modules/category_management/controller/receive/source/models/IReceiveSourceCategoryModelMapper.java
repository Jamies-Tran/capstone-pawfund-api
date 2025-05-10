package com.paw.fund.app.modules.category_management.controller.receive.source.models;

import com.paw.fund.app.modules.category_management.domain.ReceiveSourceCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IReceiveSourceCategoryModelMapper {
    ReceiveSourceCategoryResponse toResponse(ReceiveSourceCategory dto);
}

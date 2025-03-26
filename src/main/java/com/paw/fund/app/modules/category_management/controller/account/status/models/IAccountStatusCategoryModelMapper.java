package com.paw.fund.app.modules.category_management.controller.account.status.models;

import com.paw.fund.app.modules.category_management.domain.AccountStatusCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAccountStatusCategoryModelMapper {
    AccountStatusCategoryResponse toResponse(AccountStatusCategory dto);
}

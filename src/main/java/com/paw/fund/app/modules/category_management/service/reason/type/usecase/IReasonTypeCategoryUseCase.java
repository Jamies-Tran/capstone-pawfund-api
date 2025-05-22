package com.paw.fund.app.modules.category_management.service.reason.type.usecase;

import com.paw.fund.app.modules.category_management.domain.ReasonTypeCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IReasonTypeCategoryUseCase {
    List<ReasonTypeCategory> getReasonTypeCategoryList(CategorySearch search);
}

package com.paw.fund.app.modules.category_management.service.usecase;

import com.paw.fund.app.modules.category_management.domain.FormTypeCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IFormTypeCategoryUseCase {
    List<FormTypeCategory> getFormTypeList(CategorySearch search);
}

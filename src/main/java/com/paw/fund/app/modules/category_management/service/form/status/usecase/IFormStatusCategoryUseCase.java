package com.paw.fund.app.modules.category_management.service.form.status.usecase;

import com.paw.fund.app.modules.category_management.domain.FormStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IFormStatusCategoryUseCase {
    List<FormStatusCategory> findFormStatusList(CategorySearch search);
}

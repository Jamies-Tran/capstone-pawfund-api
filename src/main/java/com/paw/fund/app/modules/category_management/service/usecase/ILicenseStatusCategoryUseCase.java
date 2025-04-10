package com.paw.fund.app.modules.category_management.service.usecase;

import com.paw.fund.app.modules.category_management.domain.LicenseStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface ILicenseStatusCategoryUseCase {
    List<LicenseStatusCategory> getLicenseTypeList(CategorySearch search);
}

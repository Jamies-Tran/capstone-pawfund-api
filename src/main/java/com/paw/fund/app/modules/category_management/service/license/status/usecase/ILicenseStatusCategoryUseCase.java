package com.paw.fund.app.modules.category_management.service.license.status.usecase;

import com.paw.fund.app.modules.category_management.domain.LicenseStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface ILicenseStatusCategoryUseCase {
    List<LicenseStatusCategory> getLicenseTypeList(CategorySearch search);
}

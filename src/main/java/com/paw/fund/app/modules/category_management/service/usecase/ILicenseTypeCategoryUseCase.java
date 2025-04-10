package com.paw.fund.app.modules.category_management.service.usecase;

import com.paw.fund.app.modules.category_management.domain.LicenseTypeCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface ILicenseTypeCategoryUseCase {
    List<LicenseTypeCategory> getLicenseTypeList(CategorySearch search);
}

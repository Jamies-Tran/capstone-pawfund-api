package com.paw.fund.app.modules.category_management.service.usecase;

import com.paw.fund.app.modules.category_management.domain.ShelterRegistrationStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IShelterRegistrationStatusCategoryUseCase {
    List<ShelterRegistrationStatusCategory> getShelterRegistrationCategoryList(CategorySearch search);
}

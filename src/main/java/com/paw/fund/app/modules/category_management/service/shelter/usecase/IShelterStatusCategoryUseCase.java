package com.paw.fund.app.modules.category_management.service.shelter.usecase;

import com.paw.fund.app.modules.category_management.domain.ShelterStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IShelterStatusCategoryUseCase {
    List<ShelterStatusCategory> getShelterStatusCategoryList(CategorySearch search);
}

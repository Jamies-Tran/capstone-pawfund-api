package com.paw.fund.app.modules.category_management.service.pet.health.status.usecase;

import com.paw.fund.app.modules.category_management.domain.PetHealthStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IPetHealthStatusCategoryUseCase {
    List<PetHealthStatusCategory> getPetHealthStatusCategoryList(CategorySearch search);
}

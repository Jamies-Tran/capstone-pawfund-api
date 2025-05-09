package com.paw.fund.app.modules.category_management.service.pet.status.usecase;

import com.paw.fund.app.modules.category_management.domain.PetStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IPetStatusCategoryUseCase {
    List<PetStatusCategory> getPetStatusCategoryList(CategorySearch search);
}

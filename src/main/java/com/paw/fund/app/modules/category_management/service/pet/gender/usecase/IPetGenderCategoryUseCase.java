package com.paw.fund.app.modules.category_management.service.pet.gender.usecase;

import com.paw.fund.app.modules.category_management.domain.PetGenderCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IPetGenderCategoryUseCase {
    List<PetGenderCategory> getPetGenderCategoryList(CategorySearch search);
}

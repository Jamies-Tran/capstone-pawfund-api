package com.paw.fund.app.modules.category_management.service.pet.color.usecase;


import com.paw.fund.app.modules.category_management.domain.PetColorCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IPetColorCategoryUseCase {
    List<PetColorCategory> getPetColorCategoryList(CategorySearch search);
}

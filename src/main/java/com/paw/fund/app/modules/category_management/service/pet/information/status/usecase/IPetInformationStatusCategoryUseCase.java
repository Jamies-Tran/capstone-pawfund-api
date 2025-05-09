package com.paw.fund.app.modules.category_management.service.pet.information.status.usecase;

import com.paw.fund.app.modules.category_management.domain.PetInformationStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IPetInformationStatusCategoryUseCase {
    List<PetInformationStatusCategory> getPetInformationCategoryList(CategorySearch search);
}

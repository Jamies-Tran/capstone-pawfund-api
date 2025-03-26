package com.paw.fund.app.modules.category_management.service.gender.usecase;

import com.paw.fund.app.modules.category_management.domain.GenderCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.GenderSearch;

import java.util.List;

public interface IGenderCategoryUseCase {
    List<GenderCategory> getGenderList(GenderSearch search);
}

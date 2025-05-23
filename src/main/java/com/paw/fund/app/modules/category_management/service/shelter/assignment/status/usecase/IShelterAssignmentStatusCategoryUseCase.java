package com.paw.fund.app.modules.category_management.service.shelter.assignment.status.usecase;

import com.paw.fund.app.modules.category_management.domain.ShelterAssignmentStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IShelterAssignmentStatusCategoryUseCase {
    List<ShelterAssignmentStatusCategory> getShelterAssignmentStatusCategoryList(CategorySearch search);
}

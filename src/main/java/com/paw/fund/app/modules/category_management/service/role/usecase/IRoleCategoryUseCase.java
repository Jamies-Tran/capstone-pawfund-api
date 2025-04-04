package com.paw.fund.app.modules.category_management.service.role.usecase;

import com.paw.fund.app.modules.category_management.domain.RoleCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IRoleCategoryUseCase {
    List<RoleCategory> getRoleList(CategorySearch search);
}

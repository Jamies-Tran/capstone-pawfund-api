package com.paw.fund.app.modules.category_management.service.usecase;

import com.paw.fund.app.modules.category_management.domain.AccountStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IAccountStatusCategoryUseCase {
    List<AccountStatusCategory> getAccountStatusList(CategorySearch search);
}

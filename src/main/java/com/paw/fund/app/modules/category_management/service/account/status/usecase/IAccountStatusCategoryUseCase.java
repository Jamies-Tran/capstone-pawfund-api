package com.paw.fund.app.modules.category_management.service.account.status.usecase;

import com.paw.fund.app.modules.category_management.domain.AccountStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.AccountStatusSearch;

import java.util.List;

public interface IAccountStatusCategoryUseCase {
    List<AccountStatusCategory> getAccountStatusList(AccountStatusSearch search);
}

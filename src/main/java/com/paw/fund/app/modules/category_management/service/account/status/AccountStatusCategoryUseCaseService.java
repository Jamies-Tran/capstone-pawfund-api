package com.paw.fund.app.modules.category_management.service.account.status;

import com.paw.fund.app.modules.category_management.domain.AccountStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.account.status.usecase.IAccountStatusCategoryUseCase;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountStatusCategoryUseCaseService implements IAccountStatusCategoryUseCase {
    AccountStatusCategoryQueryService queryService;

    @Override
    public List<AccountStatusCategory> getAccountStatusList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

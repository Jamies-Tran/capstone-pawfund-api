package com.paw.fund.app.modules.log_management.service.account;

import com.paw.fund.app.modules.log_management.domain.account.AccountActivityLog;
import com.paw.fund.app.modules.log_management.domain.account.usecase.AccountActivityLogFilter;
import com.paw.fund.app.modules.log_management.service.account.usecase.IAccountActivityLogUseCase;
import com.paw.fund.configuration.request.context.RequestContext;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountActivityLogUseCaseService implements IAccountActivityLogUseCase {
    @NonNull
    AccountActivityLogQueryService queryService;

    @NonNull
    RequestContext requestContext;

    @Override
    public Page<AccountActivityLog> getSelfLog(AccountActivityLogFilter filter) {
        AccountActivityLogFilter filterHandle = filter.ofSelfLog(requestContext.getCurrentAccountLogin().accountId());

        return queryService.findAll(filterHandle.searchCriteria(), filterHandle.pageRequestCustom());
    }
}

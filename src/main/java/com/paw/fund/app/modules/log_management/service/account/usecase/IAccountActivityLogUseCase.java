package com.paw.fund.app.modules.log_management.service.account.usecase;

import com.paw.fund.app.modules.log_management.domain.account.AccountActivityLog;
import com.paw.fund.app.modules.log_management.domain.account.usecase.AccountActivityLogFilter;
import com.paw.fund.app.modules.log_management.domain.account.usecase.AccountActivityLogSearchCriteria;
import com.paw.fund.utils.request.PageRequestCustom;
import org.springframework.data.domain.Page;

public interface IAccountActivityLogUseCase {
    Page<AccountActivityLog> getSelfLog(AccountActivityLogFilter filter);
}

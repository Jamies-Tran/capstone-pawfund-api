package com.paw.fund.app.modules.account.service.usecase;

import com.paw.fund.app.modules.account.domain.Account;
import com.paw.fund.app.modules.account.domain.usecase.AccountEmail;
import com.paw.fund.app.modules.account.domain.usecase.AccountId;

public interface IAccountUseCase {
    Account getAccount(AccountId accountId);

    Account getAccountForAuth(AccountEmail accountEmail);
}

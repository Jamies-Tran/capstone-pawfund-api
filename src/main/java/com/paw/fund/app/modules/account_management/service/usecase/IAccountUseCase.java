package com.paw.fund.app.modules.account_management.service.usecase;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountEmail;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountId;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountPassword;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountUpdate;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountUpdatePassword;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountVerification;

public interface IAccountUseCase {
    Account getAccount(AccountId accountId);

    Account getAccountForAuth(AccountEmail accountEmail);

    Account createAccount(Account account);

    Account verifyCreatedAccount(AccountVerification accountVerification);

    Account verifyNewEmail(AccountVerification accountVerification);

    Account selfChangeInfo(Account account);

    Account activeAccount(AccountId accountId);

    Account inactiveAccount(AccountId accountId);

    Account selfChangePassword(AccountPassword accountPassword);

    Account changePassword(AccountUpdatePassword accountUpdatePassword);
}

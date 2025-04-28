package com.paw.fund.app.modules.account_management.service.account.usecase;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.usecase.account.AccountEmail;
import com.paw.fund.app.modules.account_management.domain.usecase.account.AccountFilter;
import com.paw.fund.app.modules.account_management.domain.usecase.account.AccountId;
import com.paw.fund.app.modules.account_management.domain.usecase.account.AccountPassword;
import com.paw.fund.app.modules.account_management.domain.usecase.account.AccountRegisterRole;
import com.paw.fund.app.modules.account_management.domain.usecase.account.AccountUpdatePassword;
import com.paw.fund.app.modules.account_management.domain.usecase.account.AccountVerification;
import org.springframework.data.domain.Page;

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

    Account getAccountDetail(AccountId accountId);

    Account getSelfDetail();

    Page<Account> getAccountList(AccountFilter filter);

    void deleteAccount(AccountId accountId);

    Account registerRole(AccountRegisterRole registerRole);
}

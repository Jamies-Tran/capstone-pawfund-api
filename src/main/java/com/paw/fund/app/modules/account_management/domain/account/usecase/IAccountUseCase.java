package com.paw.fund.app.modules.account_management.domain.account.usecase;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.account.AccountSave;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountEmail;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountFilter;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountId;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountPassword;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountRegisterRole;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountUpdatePassword;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountVerification;
import org.springframework.data.domain.Page;

public interface IAccountUseCase {

    Account getAccountForAuth(AccountEmail accountEmail);

    Account createAccount(AccountSave accountSave);

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

    Account getAccountByEmail(AccountEmail accountEmail);
}

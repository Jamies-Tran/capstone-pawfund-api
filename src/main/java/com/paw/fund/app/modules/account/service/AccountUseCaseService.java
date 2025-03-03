package com.paw.fund.app.modules.account.service;

import com.paw.fund.app.modules.account.domain.Account;
import com.paw.fund.app.modules.account.domain.usecase.AccountEmail;
import com.paw.fund.app.modules.account.domain.usecase.AccountId;
import com.paw.fund.app.modules.account.service.usecase.IAccountUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountUseCaseService implements IAccountUseCase {
    @NonNull
    AccountQueryService queryService;

    @Override
    public Account getAccount(AccountId accountId) {
        return queryService.findById(accountId.value());
    }

    @Override
    public Account getAccountForAuth(AccountEmail accountEmail) {
        return queryService.findByAccountEmail(accountEmail.value());
    }
}

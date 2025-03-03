package com.paw.fund.app.modules.account.service;

import com.paw.fund.app.modules.account.domain.Account;
import com.paw.fund.app.modules.account.domain.AccountMapper;
import com.paw.fund.app.modules.account.repository.database.IAccountRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountQueryService {
    @NonNull
    IAccountRepository repository;

    @NonNull
    AccountMapper mapper;

    public Account findById(Long accountId) {
        return repository.findById(accountId)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Account findByAccountEmail(String accountEmail) {
        return repository.findByEmail(accountEmail)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }
}

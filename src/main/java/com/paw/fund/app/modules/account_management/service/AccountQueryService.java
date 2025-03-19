package com.paw.fund.app.modules.account_management.service;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.domain.IAccountMapper;
import com.paw.fund.app.modules.account_management.repository.database.IAccountRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountQueryService {
    @NonNull
    IAccountRepository repository;

    @NonNull
    IAccountMapper mapper;

    public Account findById(Long accountId) {
        ValidationUtil.validateArgumentNotNull(accountId);
        return repository.findById(accountId)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Account findByAccountEmail(String accountEmail) {
        ValidationUtil.validateArgumentNotNull(accountEmail);
        return repository.findByEmail(accountEmail)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Optional<Account> findByAccountEmailNullable(String accountEmail) {
        ValidationUtil.validateArgumentNotNull(accountEmail);
        return repository.findByEmail(accountEmail)
                .map(mapper::toDto);
    }

    public Boolean existsByAccountId(Long accountId) {
        return Optional.ofNullable(accountId)
                .map(repository::existsById)
                .orElse(false);
    }
}

package com.paw.fund.app.modules.account_role_management.service;

import com.paw.fund.app.modules.account_role_management.domain.AccountRole;
import com.paw.fund.app.modules.account_role_management.domain.IAccountRoleMapper;
import com.paw.fund.app.modules.account_role_management.repository.database.IAccountRoleRepository;
import com.paw.fund.app.modules.role_management.service.usecase.IRoleUseCase;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountRoleQueryService {
    @NonNull
    IAccountRoleRepository repository;

    @NonNull
    IAccountRoleMapper mapper;

    public Boolean existsRoleIdByAccountId(Long roleId, Long accountId) {
        return repository.existsByRoleIdAndAccountId(roleId, accountId);
    }

    public Optional<AccountRole> findByRoleIdAndAccountIdNullable(Long roleId, Long accountId) {
        return repository.findByAccountIdAndRoleId(accountId, roleId)
                .map(mapper::toDto);
    }

    public AccountRole findByRoleIdAndAccountId(Long roleId, Long accountId) {
        return repository.findByAccountIdAndRoleId(accountId, roleId)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }
}

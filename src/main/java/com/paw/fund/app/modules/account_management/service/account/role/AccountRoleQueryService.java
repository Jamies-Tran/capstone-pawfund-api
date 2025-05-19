package com.paw.fund.app.modules.account_management.service.account.role;

import com.paw.fund.app.modules.account_management.domain.account.role.AccountRole;
import com.paw.fund.app.modules.account_management.domain.account.role.AccountRoleSummarizeInfo;
import com.paw.fund.app.modules.account_management.domain.account.role.IAccountRoleMapper;
import com.paw.fund.app.modules.account_management.repository.database.account.role.IAccountRoleRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountRoleQueryService {
    @NonNull
    IAccountRoleRepository repository;

    @NonNull
    IAccountRoleMapper mapper;

    public Optional<AccountRole> findByRoleIdAndAccountIdNullable(Long roleId, Long accountId) {
        return repository.findByAccountIdAndRoleId(accountId, roleId)
                .map(mapper::toDto);
    }

    public AccountRole findByRoleIdAndAccountId(Long roleId, Long accountId) {
        return repository.findByAccountIdAndRoleId(accountId, roleId)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public List<AccountRoleSummarizeInfo> findAllAccountRoleSummarizeInfoByShelterIdIn(List<Long> shelterIds) {
        return repository.findAllAccountRoleSummarizeInfoByShelterIdIn(shelterIds)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}

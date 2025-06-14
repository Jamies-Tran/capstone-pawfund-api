package com.paw.fund.app.modules.account_management.service.account.role;

import com.paw.fund.app.modules.account_management.domain.account.role.AccountRole;
import com.paw.fund.app.modules.account_management.repository.database.account.role.IAccountRoleMapper;
import com.paw.fund.app.modules.account_management.repository.database.account.role.AccountRoleEntity;
import com.paw.fund.app.modules.account_management.repository.database.account.role.IAccountRoleRepository;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountRoleCommandService {
    @NonNull
    IAccountRoleRepository repository;

    @NonNull
    IAccountRoleMapper mapper;

    protected List<AccountRole> saveAll(Long accountId, List<Long> roleIds) {
        ValidationUtil.validateArgumentNotNull(accountId);
        ValidationUtil.validateArgumentListNotNull(roleIds);
        List<AccountRoleEntity> newAccountRoles = roleIds.stream()
                .map(x -> AccountRoleEntity.builder()
                        .accountId(accountId)
                        .roleId(x)
                        .build())
                .toList();
        List<AccountRoleEntity> savedAccountRoles = repository.saveAll(newAccountRoles);

        return savedAccountRoles.stream()
                .map(mapper::toDto)
                .toList();
    }

    protected List<AccountRole> saveAll(Long shelterId, Long accountId, List<Long> roleIds) {
        ValidationUtil.validateArgumentNotNull(accountId);
        ValidationUtil.validateArgumentListNotNull(roleIds);
        List<AccountRoleEntity> newAccountRoles = roleIds.stream()
                .map(x -> AccountRoleEntity.builder()
                        .accountId(accountId)
                        .roleId(x)
                        .shelterId(shelterId)
                        .build())
                .toList();
        List<AccountRoleEntity> savedAccountRoles = repository.saveAll(newAccountRoles);

        return savedAccountRoles.stream()
                .map(mapper::toDto)
                .toList();
    }



    public void deleteByAccountId(Long accountId) {
        ValidationUtil.validateArgumentNotNull(accountId);
        List<AccountRoleEntity> accountRoles = repository.findAllByAccountId(accountId);

        repository.deleteAll(accountRoles);
    }

    public AccountRole save(Long accountId, Long roleId) {
        AccountRole accountRole = AccountRole.builder()
                .accountId(accountId)
                .roleId(roleId)
                .build();
        AccountRoleEntity newAccountRole = mapper.toEntity(accountRole);
        AccountRoleEntity savedAccountRole = repository.save(newAccountRole);

        return mapper.toDto(savedAccountRole);
    }
}

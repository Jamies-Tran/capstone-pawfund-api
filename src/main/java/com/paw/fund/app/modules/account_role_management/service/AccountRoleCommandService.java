package com.paw.fund.app.modules.account_role_management.service;

import com.paw.fund.app.modules.account_management.service.AccountQueryService;
import com.paw.fund.app.modules.account_role_management.domain.AccountRole;
import com.paw.fund.app.modules.account_role_management.domain.IAccountRoleMapper;
import com.paw.fund.app.modules.account_role_management.repository.database.AccountRoleEntity;
import com.paw.fund.app.modules.account_role_management.repository.database.IAccountRoleRepository;
import com.paw.fund.configuration.handler.exceptions.NullPointerException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountRoleCommandService {
    @NonNull
    IAccountRoleRepository repository;

    @NonNull
    AccountQueryService accountQueryService;

    @NonNull
    IAccountRoleMapper mapper;

    public List<AccountRole> saveAll(Long accountId, List<Long> roleIds) {
        validateArgument(accountId);
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

    private void validateArgument(Long accountId) {
        String msg = "";

        if(!accountQueryService.existsByAccountId(accountId)) {
            msg = "Tài khoản không tồn tại";
        }

        if(StringUtils.hasText(msg)) {
            throw new NullPointerException(msg);
        }
    }
}

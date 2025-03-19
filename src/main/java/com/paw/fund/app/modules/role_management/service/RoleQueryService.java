package com.paw.fund.app.modules.role_management.service;

import com.paw.fund.app.modules.role_management.domain.IRoleMapper;
import com.paw.fund.app.modules.role_management.domain.Role;
import com.paw.fund.app.modules.role_management.repository.database.IRoleRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleQueryService {
    @NonNull
    IRoleRepository repository;

    @NonNull
    IRoleMapper mapper;

    public Role findByCode(String code) {
        ValidationUtil.validateArgumentNotNull(code);
        return repository.findByRoleCode(code)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public List<Role> findAllByCodeList(List<String> codes) {
        ValidationUtil.validateArgumentListNotNull(codes);
        return repository.findAllByRoleCodeIn(codes)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<Role> findAllByAccountId(Long accountId) {
        ValidationUtil.validateArgumentNotNull(accountId);
        return repository.findAllByAccountId(accountId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}

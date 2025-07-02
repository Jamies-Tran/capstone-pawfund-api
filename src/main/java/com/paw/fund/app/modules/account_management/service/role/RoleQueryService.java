package com.paw.fund.app.modules.account_management.service.role;

import com.paw.fund.app.modules.account_management.domain.role.IRoleMapper;
import com.paw.fund.app.modules.account_management.domain.role.Role;
import com.paw.fund.app.modules.account_management.repository.database.role.IRoleRepository;
import com.paw.fund.common.aspect.annotation.validate.args.ValidateArgs;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
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
public class RoleQueryService {
    @NonNull
    IRoleRepository repository;

    @NonNull
    IRoleMapper mapper;

    @ValidateArgs
    protected Role findByCode(String code) {
        return repository.findByRoleCode(code)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @ValidateArgs
    protected List<Role> findAllByCodeIn(List<String> codes) {
        return repository.findAllByRoleCodeIn(codes)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @ValidateArgs
    protected List<Role> findAllByAccountId(Long accountId) {
        return repository.findAllByAccountId(accountId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}

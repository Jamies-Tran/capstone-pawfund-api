package com.paw.fund.app.modules.login_info_management.service;

import com.paw.fund.app.modules.login_info_management.domain.ILoginInfoMapper;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.Account;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.Login;
import com.paw.fund.app.modules.login_info_management.repository.database.ILoginInfoRepository;
import com.paw.fund.app.modules.login_info_management.repository.database.LoginInfoEntity;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.CurrentAccountLogin;
import com.paw.fund.common.aspect.annotation.validate.args.ValidateArgs;
import com.paw.fund.common.context.request.RequestContext;
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
public class LoginInfoQueryService {
    @NonNull
    ILoginInfoRepository repository;

    @NonNull
    ILoginInfoMapper mapper;

    @ValidateArgs
    protected Optional<LoginInfoEntity> findByAccountIdNullable(Long accountId) {
        ValidationUtil.validateArgumentNotNull(accountId);
        return repository.findByAccountId(accountId);
    }

    @ValidateArgs
    protected Login findByRefreshToken(String refreshToken) {
        LoginInfoEntity foundSession = repository.findByRefreshToken(refreshToken)
                .orElseThrow(ResourceNotFoundException::new);

        return mapper.toDto(foundSession);
    }

    protected CurrentAccountLogin findCurrentAccountLogin() {
        return repository.findCurrentAccountLoginByEmail(RequestContext.getCurrentAccountLogin())
                .stream()
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }

    @ValidateArgs
    protected Account findAccountByEmail(String email) {
        return repository.findAccountByEmail(email)
                .orElseThrow(ResourceNotFoundException::new);
    }

    protected Login findTopOrderByCreatedAtDesc(String email) {
        return repository.findFirstByAccountEmailOrderByCreatedAtDesc(email)
                .map(mapper::toDto)
                .orElse(null);
    }
}

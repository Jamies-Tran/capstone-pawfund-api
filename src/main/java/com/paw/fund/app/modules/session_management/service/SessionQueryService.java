package com.paw.fund.app.modules.session_management.service;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.session_management.domain.ISessionMapper;
import com.paw.fund.app.modules.session_management.domain.Session;
import com.paw.fund.app.modules.session_management.repository.database.ISessionRepository;
import com.paw.fund.app.modules.session_management.repository.database.SessionEntity;
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
public class SessionQueryService {
    @NonNull
    ISessionRepository repository;

    @NonNull
    ISessionMapper mapper;

    public Optional<SessionEntity> findEntityByAccountIdNullable(Long accountId) {
        ValidationUtil.validateArgumentNotNull(accountId);
        return repository.findByAccountId(accountId);
    }

    public Session findByRefreshToken(String refreshToken) {
        ValidationUtil.validateArgumentNotNull(refreshToken);
        SessionEntity foundSession = repository.findByRefreshToken(refreshToken)
                .orElseThrow(ResourceNotFoundException::new);

        return mapper.toDto(foundSession);
    }
}

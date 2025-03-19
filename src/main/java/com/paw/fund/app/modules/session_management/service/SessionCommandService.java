package com.paw.fund.app.modules.session_management.service;

import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.app.modules.session_management.domain.ISessionMapper;
import com.paw.fund.app.modules.session_management.domain.Session;
import com.paw.fund.app.modules.session_management.repository.database.ISessionRepository;
import com.paw.fund.app.modules.session_management.repository.database.SessionEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.dto.CurrentAccountLogin;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SessionCommandService {
    @NonNull
    ISessionRepository repository;

    @NonNull
    ISessionMapper mapper;

    @NonNull
    IAuditableUseCase auditableUseCase;

    @NonNull
    RequestContext requestContext;

    public Session save(Session session) {
        ValidationUtil.validateNotNullPointerException(session);
        SessionEntity newSession = mapper.toEntity(session);
        newSession.prepareSave(auditableUseCase.createAuditableForNew());
        SessionEntity savedSession = repository.save(newSession);

        return mapper.toDto(savedSession);
    }

    public Session update(SessionEntity oldSession, Session session) {
        ValidationUtil.validateNotNullPointerException(oldSession);
        ValidationUtil.validateNotNullPointerException(session);
        oldSession.prepareUpdate(auditableUseCase.createAuditableForUpdate());
        mapper.update(oldSession, session);
        SessionEntity savedSession = repository.save(oldSession);

        return mapper.toDto(savedSession);
    }

    public Long delete() {
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        SessionEntity session = repository.findByAccountId(currentAccountLogin.accountId())
                        .orElseThrow(ResourceNotFoundException::new);
        repository.delete(session);

        return currentAccountLogin.accountId();
    }

}

package com.paw.fund.app.modules.session_management.service;


import com.paw.fund.app.modules.session_management.domain.ISessionMapper;
import com.paw.fund.app.modules.session_management.domain.Session;
import com.paw.fund.app.modules.session_management.repository.database.ISessionRepository;
import com.paw.fund.app.modules.session_management.repository.database.SessionEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.common.context.request.RequestContext;
import com.paw.fund.common.CurrentAccountLogin;
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
    RequestContext requestContext;

    public Session save(Session session) {
        ValidationUtil.validateNotNullPointerException(session);
        SessionEntity newSession = mapper.toEntity(session);
        SessionEntity savedSession = repository.save(newSession);

        return mapper.toDto(savedSession);
    }

    public Session update(SessionEntity oldSession, Session session) {
        ValidationUtil.validateNotNullPointerException(oldSession);
        ValidationUtil.validateNotNullPointerException(session);
        mapper.update(oldSession, session);
        SessionEntity savedSession = repository.save(oldSession);

        return mapper.toDto(savedSession);
    }

    public Long delete() {
//        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        SessionEntity session = repository.findByAccountId(0L)
                        .orElseThrow(ResourceNotFoundException::new);
        repository.delete(session);

        return 0L;
    }

}

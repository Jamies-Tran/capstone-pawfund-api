package com.paw.fund.app.modules.session_management.service;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.service.AccountQueryService;
import com.paw.fund.app.modules.log_management.domain.account.AccountActivityLog;
import com.paw.fund.app.modules.log_management.service.account.AccountActivityLogCommandService;
import com.paw.fund.app.modules.session_management.domain.Session;
import com.paw.fund.app.modules.session_management.domain.usecase.LoginInfo;
import com.paw.fund.app.modules.session_management.domain.usecase.RefreshToken;
import com.paw.fund.app.modules.session_management.repository.database.SessionEntity;
import com.paw.fund.app.modules.session_management.service.usecase.ISessionUseCase;
import com.paw.fund.configuration.handler.exceptions.AuthenticationException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.enums.EAccountStatus;
import com.paw.fund.enums.EAction;
import com.paw.fund.utils.password.encoder.PawFundPasswordEncoder;
import com.paw.fund.utils.token.TokenUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SessionUseCaseService implements ISessionUseCase {
    @NonNull
    SessionCommandService commandService;

    @NonNull
    SessionQueryService queryService;

    @NonNull
    PawFundPasswordEncoder appPasswordEncoder;

    @NonNull
    AccountQueryService accountQueryService;

    @NonNull
    AccountActivityLogCommandService accountActivityLogCommandService;

    @NonNull
    TokenUtil tokenUtil;

    @Override
    public Session login(LoginInfo login) {
        Account account = accountQueryService.findByAccountEmail(login.email());
        if(!Objects.equals(account.statusCode(), EAccountStatus.ACTIVE.getCode())) {
            throw new AuthenticationException("Tài khoản chưa được kích hoạt");
        } else if(!appPasswordEncoder.bCryptpasswordEncoder().matches(login.password(), account.password())) {
            throw new AuthenticationException();
        }
        String accessToken = tokenUtil.generateAccessToken(account.email());
        String refreshToken = UUID.randomUUID().toString();
        LocalDateTime accessExpiredAt = tokenUtil.accessTokenExpiredAt().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        LocalDateTime refreshExpiredAt = tokenUtil.refreshTokenExpiredAt().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        Session newSession = Session.builder()
                .accountId(account.accountId())
                .refreshToken(refreshToken)
                .refreshExpiredAt(refreshExpiredAt)
                .build();
        Optional<SessionEntity> existedSession = queryService.findEntityByAccountIdNullable(account.accountId());
        Session session;
        if(existedSession.isPresent()) {
            session = commandService.update(existedSession.get(), newSession);
        } else {
            session = commandService.save(newSession);
        }
        AccountActivityLog log = AccountActivityLog.builder()
                .accountId(account.accountId())
                .actionCode(EAction.LOGIN.getCode())
                .actionName(EAction.LOGIN.getName())
                .build();
        accountActivityLogCommandService.save(log);

        return session
                .withAccessToken(accessToken)
                .withAccessExpiredAt(accessExpiredAt)
                .withAccount(account);
    }

    @Override
    public Session refresh(RefreshToken refreshToken) {
        Session foundSession = queryService.findByRefreshToken(refreshToken.value());
        if(LocalDateTime.now().isAfter(foundSession.refreshExpiredAt())) {
            throw new ResourceNotValidException("Token đã hết hạn");
        }
        Account account = accountQueryService.findById(foundSession.accountId());
        String accessToken = tokenUtil.generateAccessToken(account.email());
        LocalDateTime accessExpiredAt = tokenUtil.accessTokenExpiredAt().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return Session.builder()
                .accessToken(accessToken)
                .accessExpiredAt(accessExpiredAt)
                .build();
    }

    @Override
    public void logout() {
        Long deletedSessionAccountId = commandService.delete();
        AccountActivityLog log = AccountActivityLog.builder()
                .accountId(deletedSessionAccountId)
                .actionCode(EAction.LOGOUT.getCode())
                .actionName(EAction.LOGOUT.getName())
                .build();
        accountActivityLogCommandService.save(log);
    }
}

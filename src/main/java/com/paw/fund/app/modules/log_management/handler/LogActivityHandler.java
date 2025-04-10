package com.paw.fund.app.modules.log_management.handler;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.log_management.annotation.LogAction;
import com.paw.fund.app.modules.log_management.domain.account.AccountActivityLog;
import com.paw.fund.app.modules.log_management.service.account.AccountActivityLogCommandService;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.dto.CurrentAccountLogin;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LogActivityHandler {
    @NonNull
    AccountActivityLogCommandService commandService;

    @NonNull
    RequestContext requestContext;

    @Around("@annotation(com.paw.fund.app.modules.log_management.annotation.LogAction)")
    public Object aroundAction(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        LogAction logAction = methodSignature.getMethod().getAnnotation(LogAction.class);
        Object result;
        try {
            result = joinPoint.proceed();
            CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
            if(result instanceof Account) {
                Long refId = logAction.isCurrentLogin() ? currentAccountLogin.accountId()
                        : ((Account) result).accountId();
                AccountActivityLog log = AccountActivityLog.builder()
                        .actionCode(logAction.action().getCode())
                        .actionName(logAction.action().getName())
                        .loggedAt(LocalDateTime.now())
                        .accountId(refId)
                        .build();
                commandService.save(log);

                return result;
            } else {
                AccountActivityLog log = AccountActivityLog.builder()
                        .actionCode(logAction.action().getCode())
                        .actionName(logAction.action().getName())
                        .loggedAt(LocalDateTime.now())
                        .accountId(currentAccountLogin.accountId())
                        .build();
                commandService.save(log);

                return result;
            }
        } catch (Throwable e) {
            throw e;
        }

    }
}

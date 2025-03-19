package com.paw.fund.app.modules.auditable_management.service;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.service.AccountQueryService;
import com.paw.fund.app.modules.auditable_management.repository.database.AuditableEntity;
import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.dto.CurrentAccountLogin;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuditableUseCaseService implements IAuditableUseCase {
    @NonNull
    RequestContext requestContext;

    @Override
    public AuditableEntity createAuditableForNew() {
        Optional<CurrentAccountLogin> currentAccountLogin = Optional.ofNullable(requestContext.getCurrentAccountLogin());
        AuditableEntity auditable = new AuditableEntity();
        currentAccountLogin.ifPresentOrElse(
                x -> {
                    auditable.setCreatedById(x.accountId());
                    auditable.setCreatedByName(x.fullName());
                    auditable.setCreatedAt(LocalDateTime.now());
                    auditable.setUpdatedById(x.accountId());
                    auditable.setUpdatedByName(x.fullName());
                    auditable.setUpdatedAt(LocalDateTime.now());
                },
                () -> {
                    auditable.setCreatedById(-1L);
                    auditable.setCreatedByName("system");
                    auditable.setCreatedAt(LocalDateTime.now());
                    auditable.setUpdatedById(-1L);
                    auditable.setUpdatedByName("system");
                    auditable.setUpdatedAt(LocalDateTime.now());
                }
        );

        return auditable;
    }

    @Override
    public AuditableEntity createAuditableForUpdate() {
        Optional<CurrentAccountLogin> currentAccountLogin = Optional.ofNullable(requestContext.getCurrentAccountLogin());
        AuditableEntity auditable = new AuditableEntity();
        currentAccountLogin.ifPresentOrElse(
                x -> {
                    auditable.setUpdatedById(x.accountId());
                    auditable.setUpdatedByName(x.fullName());
                    auditable.setUpdatedAt(LocalDateTime.now());
                },
                () -> {
                    auditable.setCreatedById(-1L);
                    auditable.setCreatedByName("system");
                    auditable.setCreatedAt(LocalDateTime.now());
                    auditable.setUpdatedById(-1L);
                    auditable.setUpdatedByName("system");
                    auditable.setUpdatedAt(LocalDateTime.now());
                }
        );


        return auditable;
    }
}

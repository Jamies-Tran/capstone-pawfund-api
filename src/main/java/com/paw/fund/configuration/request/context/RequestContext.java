package com.paw.fund.configuration.request.context;

import com.paw.fund.app.modules.account_management.domain.usecase.AccountEmail;
import com.paw.fund.app.modules.account_management.service.AccountQueryService;
import com.paw.fund.app.modules.account_management.service.usecase.IAccountUseCase;
import com.paw.fund.app.modules.role_management.service.RoleQueryService;
import com.paw.fund.dto.CurrentAccountLogin;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestContext {
    @NonNull
    AccountQueryService queryService;

    @NonNull
    RoleQueryService roleQueryService;



    public CurrentAccountLogin getCurrentAccountLogin() {
        if(Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).isPresent()) {
            String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            CurrentAccountLogin currentAccountLogin = queryService.findByAccountEmailNullable(email)
                    .map(CurrentAccountLogin::of)
                    .orElse(null);
            return Optional.ofNullable(currentAccountLogin)
                    .map(x -> x.withRoles(roleQueryService.findAllByAccountId(x.accountId())))
                    .orElse(currentAccountLogin);
        }

        return null;
    }
}

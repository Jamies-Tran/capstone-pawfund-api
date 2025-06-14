package com.paw.fund.common.context.request;

import com.paw.fund.app.modules.account_management.domain.account.usecase.IAccountUseCase;
import com.paw.fund.app.modules.account_management.domain.role.usecase.IRoleUseCase;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountEmail;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountId;
import com.paw.fund.common.CurrentAccountLogin;
import com.paw.fund.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestContext {

    public static String getCurrentAccountLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(ObjectUtils.isNull(auth) || !auth.isAuthenticated()) {
            return null;
        }
        return (String) auth.getPrincipal();
    }
}

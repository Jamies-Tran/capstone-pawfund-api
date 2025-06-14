package com.paw.fund.app.modules.account_management.service.account.role;

import com.paw.fund.app.modules.account_management.domain.account.role.event.listener.CreateAccountRoleEventListener;
import com.paw.fund.app.modules.account_management.domain.account.role.usecase.IAccountRoleUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountRoleUseCaseService implements IAccountRoleUseCase {
    @NonNull
    AccountRoleCommandService commandService;

    @Override
    @Transactional
    @EventListener
    public void createAccountRoleList(CreateAccountRoleEventListener accountRoleEventListener) {
        commandService.saveAll(accountRoleEventListener.getAccountId(), accountRoleEventListener.getRoleIds());
    }
}

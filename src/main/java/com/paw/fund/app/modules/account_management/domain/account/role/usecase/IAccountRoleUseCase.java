package com.paw.fund.app.modules.account_management.domain.account.role.usecase;

import com.paw.fund.app.modules.account_management.domain.account.role.event.listener.CreateAccountRoleEventListener;

public interface IAccountRoleUseCase {
    void createAccountRoleList(CreateAccountRoleEventListener accountRoleEventListener);
}

package com.paw.fund.app.modules.account_management.domain.account.role.event.listener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAccountRoleEventListener extends ApplicationEvent {
    Long accountId;
    List<Long> roleIds;

    public CreateAccountRoleEventListener(Object source, Long accountId, List<Long> roleIds) {
        super(source);
        this.accountId = accountId;
        this.roleIds = roleIds;
    }
}

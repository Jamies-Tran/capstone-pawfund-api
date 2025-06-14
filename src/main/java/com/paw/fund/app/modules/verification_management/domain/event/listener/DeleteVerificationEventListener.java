package com.paw.fund.app.modules.verification_management.domain.event.listener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeleteVerificationEventListener extends ApplicationEvent {
    String verificationCode;

    public DeleteVerificationEventListener(Object source, String verificationCode) {
        super(source);
        this.verificationCode = verificationCode;
    }
}

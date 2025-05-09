package com.paw.fund.app.modules.log_management.annotation;

import com.paw.fund.enums.EPetAction;
import com.paw.fund.enums.EPetStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePetActivityLogHelper {
    EPetAction action();

    EPetStatus status() default EPetStatus.NONE;
}

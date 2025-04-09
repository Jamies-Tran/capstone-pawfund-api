package com.paw.fund.app.modules.log_management.annotation;

import com.paw.fund.enums.EAction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAction {
    EAction action();
    boolean isCurrentLogin() default false;
}

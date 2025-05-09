package com.paw.fund.app.modules.log_management.annotation;

import com.paw.fund.enums.EAccountAction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateAccountActivityLogHelper {
    EAccountAction action();
    boolean isCurrentLogin() default false;
}

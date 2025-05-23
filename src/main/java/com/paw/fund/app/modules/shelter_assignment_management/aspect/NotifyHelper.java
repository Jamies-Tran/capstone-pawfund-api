package com.paw.fund.app.modules.shelter_assignment_management.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotifyHelper {
    String appDestination() default "";

    String variableAppDestination() default "";

    String userDestination() default "";
}

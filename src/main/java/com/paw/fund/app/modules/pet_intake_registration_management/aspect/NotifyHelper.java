package com.paw.fund.app.modules.pet_intake_registration_management.aspect;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotifyHelper {
    String appDestination() default "";

    String[] multiAppDestinations() default "";

    String userDestination() default "";
}

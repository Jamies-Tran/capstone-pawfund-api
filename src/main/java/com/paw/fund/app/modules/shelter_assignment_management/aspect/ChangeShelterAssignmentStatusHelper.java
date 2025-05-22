package com.paw.fund.app.modules.shelter_assignment_management.aspect;

import com.paw.fund.enums.EShelterAssignmentStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChangeShelterAssignmentStatusHelper {
    EShelterAssignmentStatus status();
}

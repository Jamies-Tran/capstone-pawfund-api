package com.paw.fund.common.aspect.annotation.validate.args;

import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.utils.CollectionUtils;
import com.paw.fund.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class ValidateArgsAspect {
    @Before("@annotation(ValidateArgs)")
    public void validateArgs(JoinPoint joinPoint) {
        List<Object> argList = Arrays.asList(joinPoint.getArgs());
        if(CollectionUtils.hasElement(argList)) {
            argList.forEach(arg -> {
                if(ObjectUtils.isNull(arg)) {
                    throw new ResourceNotValidException();
                }
            });
        }
    }
}

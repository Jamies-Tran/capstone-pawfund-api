package com.paw.fund.app.modules.form_management.aspect.handler;

import com.paw.fund.app.modules.form_management.domain.answer.Answer;
import com.paw.fund.app.modules.form_management.domain.form.reply.FormReply;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormReplyUpdate;
import com.paw.fund.app.modules.form_management.service.answer.AnswerCommandService;
import com.paw.fund.app.modules.form_management.service.answer.AnswerQueryService;
import com.paw.fund.configuration.handler.exceptions.ServiceException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormAspectHandler {
    @NonNull
    AnswerCommandService answerCommandService;

    @NonNull
    AnswerQueryService answerQueryService;


    @Around("@annotation(com.paw.fund.app.modules.form_management.aspect.CreateAnswer)")
    public Object handleCreateAnswer(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            Object args = joinPoint.getArgs()[0];

            if(result instanceof FormReply formReply && args instanceof FormReply formReplyArgs) {
                List<Answer> answer = answerCommandService
                        .save(formReply.formResponseId(), formReplyArgs.answers());
                return formReply.withAnswers(answer);
            }

            throw new ServiceException();
        } catch (Throwable e) {
            throw e;
        }
    }

    @Around("@annotation(com.paw.fund.app.modules.form_management.aspect.GetFormReplyAdditionalData)")
    public Object handleGetFormReplyAdditionalData(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            if(result instanceof FormReply formReply) {
                List<Answer> answers = answerQueryService.findAllByFormResponseIdFromDAO(formReply.formResponseId());
                return formReply.withAnswers(answers);
            }
            throw new ServiceException();
        } catch (Throwable e) {
            throw e;
        }
    }

    @Around("@annotation(com.paw.fund.app.modules.form_management.aspect.UpdateFormReplyAdditionalData)")
    public Object handleUpdateFormReplyAdditionalData(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            Object args = joinPoint.getArgs()[0];
            if(result instanceof FormReply formReply
                    && args instanceof FormReplyUpdate formReplyUpdate) {
                List<Answer> newAnswers = formReplyUpdate.formReply().answers();
                List<Answer> answers = answerCommandService.updateAllByFormResponseId(formReply.formResponseId(), newAnswers);

                return formReply.withAnswers(answers);
            }
            throw new ServiceException();
        } catch (Throwable e) {
            throw e;
        }
    }
}

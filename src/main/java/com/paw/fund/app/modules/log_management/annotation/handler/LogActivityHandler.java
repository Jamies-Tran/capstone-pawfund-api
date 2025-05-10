package com.paw.fund.app.modules.log_management.annotation.handler;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.log_management.annotation.CreatePetActivityLogHelper;
import com.paw.fund.app.modules.log_management.annotation.CreateAccountActivityLogHelper;
import com.paw.fund.app.modules.log_management.domain.account.AccountActivityLog;
import com.paw.fund.app.modules.log_management.domain.pet.PetActivityLog;
import com.paw.fund.app.modules.log_management.service.account.AccountActivityLogCommandService;
import com.paw.fund.app.modules.log_management.service.pet.PetActivityLogCommandService;
import com.paw.fund.app.modules.pet_management.domain.health.record.PetHealthRecord;
import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import com.paw.fund.configuration.handler.exceptions.ServiceException;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.dto.CurrentAccountLogin;
import com.paw.fund.enums.EHealthStatus;
import com.paw.fund.enums.EPetAction;
import com.paw.fund.enums.EPetStatus;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LogActivityHandler {
    @NonNull
    AccountActivityLogCommandService accountActivityLogcommandService;

    @NonNull
    PetActivityLogCommandService petActivityLogCommandService;

    @NonNull
    RequestContext requestContext;

    @AfterReturning(
            returning = "result",
            pointcut = "@annotation(com.paw.fund.app.modules.log_management.annotation.CreateAccountActivityLogHelper)")
    public void createAccountActivityLogHelper(Object result, JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        CreateAccountActivityLogHelper createAccountActivityLogHelper = methodSignature.getMethod().getAnnotation(CreateAccountActivityLogHelper.class);
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        if(result instanceof Account account) {
            Long refId = createAccountActivityLogHelper.isCurrentLogin() ? currentAccountLogin.accountId()
                    : account.accountId();
            AccountActivityLog log = AccountActivityLog.builder()
                    .actionCode(createAccountActivityLogHelper.action().getCode())
                    .actionName(createAccountActivityLogHelper.action().getName())
                    .loggedAt(LocalDateTime.now())
                    .accountId(refId)
                    .build();
            accountActivityLogcommandService.save(log);
        }
    }

    @AfterReturning(
            pointcut = "@annotation(com.paw.fund.app.modules.log_management.annotation.CreatePetActivityLogHelper)",
            returning = "result")
    public void createPetActivityLogHelper(JoinPoint joinPoint, Object result) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        CreatePetActivityLogHelper annotation = methodSignature.getMethod()
                .getAnnotation(CreatePetActivityLogHelper.class);

        PetActivityLog petActivityLog = null;
        if(result instanceof Pet pet) {
            String description = generateDescription(annotation.action(), EHealthStatus.NONE, annotation.status());
            petActivityLog = PetActivityLog.builder()
                    .petId(pet.petId())
                    .actionCode(annotation.action().getCode())
                    .actionName(annotation.action().getName())
                    .description(description)
                    .build();
        } else if(result instanceof PetHealthRecord petHealthRecord) {

            String description = generateDescription(annotation.action(),
                    EHealthStatus.getValueOf(petHealthRecord.healthStatusCode()), annotation.status());
            petActivityLog = PetActivityLog.builder()
                    .petId(petHealthRecord.petId())
                    .actionCode(annotation.action().getCode())
                    .actionName(annotation.action().getName())
                    .description(description)
                    .build();
        } else {
            throw new ServiceException();
        }

        petActivityLogCommandService.save(petActivityLog);
    }

    private String generateDescription(EPetAction action, EHealthStatus health, EPetStatus status) {
        switch (action) {
            case CREATED -> {
                return "Đã thêm mới";
            }
            case UPDATED -> {
                return "Đã cập nhật";
            }

            case DELETED -> {
                return "Đã xóa";
            }

            case UPDATED_HEALTH_STATUS -> {
                if(Objects.equals(health, EHealthStatus.NONE)) {
                    return "";
                }
                return "Đã cập nhật trạng thái sức khỏe %s".formatted(health.getName());
            }

            case UPDATED_STATUS -> {
                if(Objects.equals(status, EPetStatus.NONE)) {
                    return "";
                }
                return "Đã cập nhật trạng thái %s".formatted(status.getName());
            }

            default -> {
                return "";
            }
        }
    }
}

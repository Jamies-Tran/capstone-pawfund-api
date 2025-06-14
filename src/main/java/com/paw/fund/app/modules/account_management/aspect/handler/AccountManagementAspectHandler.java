package com.paw.fund.app.modules.account_management.aspect.handler;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.account.AccountSave;
import com.paw.fund.app.modules.account_management.domain.role.Role;
import com.paw.fund.app.modules.account_management.service.account.AccountCommandService;
import com.paw.fund.app.modules.account_management.service.account.role.AccountRoleCommandService;
import com.paw.fund.app.modules.account_management.service.role.RoleQueryService;
import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.media_management.service.common.CommonMediaCommandService;
import com.paw.fund.configuration.handler.exceptions.ServiceException;
import com.paw.fund.enums.ERole;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountManagementAspectHandler {

//    @Around("@annotation(com.paw.fund.app.modules.account_management.aspect.CreateAccountHelper)")
//    public Object createAccountCreateHelper(ProceedingJoinPoint joinPoint) throws Throwable {
//        try {
//            Object result = joinPoint.proceed();
//            Object arg = joinPoint.getArgs()[0];
//            if(result instanceof Account createdAccount && arg instanceof AccountSave accountSave) {
//                Account newAccount = accountSave.account();
//
//                List<Role> roles = List.of();
//
//                List<CommonMedia> commonMedias = List.of();
//
//                if(!CollectionUtils.isEmpty(newAccount.roles())) {
//                    List<String> roleCodes = newAccount.roles()
//                            .stream()
//                            .map(Role::roleCode)
//                            .toList();
//                    roles = roleQueryService.findAllByCodeIn(roleCodes);
//                    List<Long> roleIds = roles
//                            .stream()
//                            .map(Role::roleId)
//                            .toList();
//                    if(roleCodes.contains(ERole.STAFF.getCode()) && Objects.nonNull(accountSave.shelterId())) {
//                        accountRoleCommandService.saveAll(accountSave.shelterId(), createdAccount.accountId(), roleIds);
//                    } else {
//                        accountRoleCommandService.saveAll(createdAccount.accountId(), roleIds);
//                    }
//                }
//
//                if(!CollectionUtils.isEmpty(newAccount.medias())) {
//                    commonMedias = commonMediaCommandService.saveAllWithAccountId(createdAccount.accountId(),
//                            newAccount.medias());
//                }
//                return createdAccount
//                        .withMedias(commonMedias)
//                        .withRoles(roles);
//            }
//            throw new ServiceException();
//        } catch (Throwable e) {
//            throw e;
//        }
//    }
}

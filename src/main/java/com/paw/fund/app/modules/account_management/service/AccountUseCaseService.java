package com.paw.fund.app.modules.account_management.service;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountEmail;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountId;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountPassword;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountUpdate;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountUpdatePassword;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountVerification;
import com.paw.fund.app.modules.account_management.service.usecase.IAccountUseCase;
import com.paw.fund.app.modules.account_role_management.domain.AccountRole;
import com.paw.fund.app.modules.account_role_management.service.AccountRoleCommandService;
import com.paw.fund.app.modules.log_management.domain.account.AccountActivityLog;
import com.paw.fund.app.modules.log_management.service.account.AccountActivityLogCommandService;
import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.media_management.service.common.CommonMediaCommandService;
import com.paw.fund.app.modules.role_management.domain.Role;
import com.paw.fund.app.modules.role_management.service.RoleQueryService;
import com.paw.fund.app.modules.verification_code_management.domain.VerificationCode;
import com.paw.fund.app.modules.verification_code_management.service.VerificationCodeCommandService;
import com.paw.fund.app.modules.verification_code_management.service.VerificationCodeQueryService;
import com.paw.fund.configuration.handler.exceptions.ServiceException;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.dto.CurrentAccountLogin;
import com.paw.fund.enums.EAction;
import com.paw.fund.enums.EAccountStatus;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountUseCaseService implements IAccountUseCase {
    @NonNull
    AccountQueryService queryService;

    @NonNull
    AccountCommandService commandService;

    @NonNull
    AccountRoleCommandService accountRoleCommandService;

    @NonNull
    RoleQueryService roleQueryService;

    @NonNull
    VerificationCodeQueryService verificationCodeQueryService;

    @NonNull
    VerificationCodeCommandService verificationCodeCommandService;

    @NonNull
    CommonMediaCommandService commonMediaCommandService;

    @NonNull
    AccountActivityLogCommandService accountActivityLogCommandService;

    @NonNull
    RequestContext requestContext;

    @Override
    public Account getAccount(AccountId accountId) {
        ValidationUtil.validateNotNullPointerException(accountId);
        return queryService.findById(accountId.value());
    }

    @Override
    public Account getAccountForAuth(AccountEmail accountEmail) {
        ValidationUtil.validateNotNullPointerException(accountEmail);
        Account foundAccount = queryService.findByAccountEmail(accountEmail.value());
        List<Role> roles = roleQueryService.findAllByAccountId(foundAccount.accountId());

        return foundAccount.withRoles(roles);
    }

    @Override
    @Transactional
    public Account createAccount(Account account) {
        ValidationUtil.validateNotNullPointerException(account);

        if(!CollectionUtils.isEmpty(account.roles())) {
            List<String> roleCodes = account.roles()
                    .stream()
                    .map(Role::roleCode)
                    .toList();
            List<Role> roles = roleQueryService.findAllByCodeList(roleCodes);
            List<Long> roleIds = roles
                    .stream()
                    .map(Role::roleId)
                    .toList();
            Account createdAccount = commandService.save(account);
            List<AccountRole> createdAccountRoles = accountRoleCommandService
                    .saveAll(createdAccount.accountId(), roleIds);
            AccountActivityLog log = AccountActivityLog.builder()
                    .accountId(createdAccount.accountId())
                    .actionCode(EAction.CREATED.getCode())
                    .actionName(EAction.CREATED.getName())
                    .build();
            accountActivityLogCommandService.save(log);
            if(Objects.nonNull(createdAccountRoles) && !CollectionUtils.isEmpty(createdAccountRoles)) {
                if(!CollectionUtils.isEmpty(account.medias())) {
                    List<CommonMedia> commonMedias = commonMediaCommandService.saveAllForAccount(createdAccount.accountId(),
                            account.medias());
                    return createdAccount
                            .withMedias(commonMedias)
                            .withRoles(roles);
                }
                return createdAccount.withRoles(roles);
            } else {
                throw new ServiceException();
            }
        } else {
            Account createdAccount = commandService.save(account);

            if(!CollectionUtils.isEmpty(account.medias())) {
                List<CommonMedia> commonMedias = commonMediaCommandService.saveAllForAccount(createdAccount.accountId(),
                        account.medias());
                return createdAccount
                        .withMedias(commonMedias);
            }

            return createdAccount;
        }

    }

    @Override
    @Transactional
    public Account verifyCreatedAccount(AccountVerification accountVerification) {
        ValidationUtil.validateNotNullPointerException(accountVerification);
        VerificationCode verificationCode = verificationCodeQueryService
                .findByCodeAndAccountId(accountVerification.verificationCode(), accountVerification.accountId());
        Account foundAccount = queryService.findById(verificationCode.accountId());
        Account updatedAccount = commandService.updateStatus(foundAccount.accountId(), EAccountStatus.ACTIVE);
        verificationCodeCommandService.delete(verificationCode.verificationCodeId());
        AccountActivityLog log = AccountActivityLog.builder()
                .accountId(updatedAccount.accountId())
                .actionCode(EAction.VERIFIED.getCode())
                .actionName(EAction.VERIFIED.getName())
                .build();
        accountActivityLogCommandService.save(log);

        return updatedAccount;
    }

    @Override
    public Account selfChangeInfo(Account account) {
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        Account updatedAccount = commandService.update(currentAccountLogin.accountId(), account);

        return updatedAccount;
    }

    @Override
    public Account activeAccount(AccountId accountId) {
        ValidationUtil.validateNotNullPointerException(accountId);
        Account updatedAccount = commandService.updateStatus(accountId.value(), EAccountStatus.ACTIVE);

        return updatedAccount;
    }

    @Override
    public Account inactiveAccount(AccountId accountId) {
        ValidationUtil.validateNotNullPointerException(accountId);
        Account updatedAccount = commandService.updateStatus(accountId.value(), EAccountStatus.INACTIVE);

        return updatedAccount;
    }

    @Override
    public Account selfChangePassword(AccountPassword accountPassword) {
        ValidationUtil.validateNotNullPointerException(accountPassword);
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        Account account = commandService.updatePassword(currentAccountLogin.accountId(), accountPassword.value());

        return account;
    }

    @Override
    public Account changePassword(AccountUpdatePassword accountUpdatePassword) {
        ValidationUtil.validateNotNullPointerException(accountUpdatePassword);
        Account account = commandService.updatePassword(accountUpdatePassword.accountId(),
                accountUpdatePassword.password());

        return account;
    }
}

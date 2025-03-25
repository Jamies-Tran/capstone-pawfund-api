package com.paw.fund.app.modules.account_management.service;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountEmail;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountFilter;
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
import com.paw.fund.app.modules.media_management.service.common.CommonMediaQueryService;
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
import com.paw.fund.enums.EVerificationCodeType;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
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
    CommonMediaQueryService commonMediaQueryService;

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
                .findByCodeAndAccountIdAndVerificationCodeType(
                        accountVerification.verificationCode(),
                        accountVerification.accountId(),
                        EVerificationCodeType.ACCOUNT_CREATION);
        Account foundAccount = queryService.findById(verificationCode.accountId());
        Account updatedAccount = commandService.updateStatus(foundAccount.accountId(), EAccountStatus.ACTIVE);
        verificationCodeCommandService.delete(verificationCode.verificationCodeId());
        AccountActivityLog log = AccountActivityLog.builder()
                .accountId(updatedAccount.accountId())
                .actionCode(EAction.VERIFIED_ACCOUNT.getCode())
                .actionName(EAction.VERIFIED_ACCOUNT.getName())
                .build();
        accountActivityLogCommandService.save(log);

        return updatedAccount;
    }

    @Override
    public Account verifyNewEmail(AccountVerification accountVerification) {
        ValidationUtil.validateNotNullPointerException(accountVerification);
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        VerificationCode verificationCode = verificationCodeQueryService
                .findByCodeAndAccountIdAndVerificationCodeType(
                        accountVerification.verificationCode(),
                        currentAccountLogin.accountId(),
                        EVerificationCodeType.EMAIL_UPDATE);
        Account foundAccount = queryService.findById(currentAccountLogin.accountId());
        Account updatedAccount = commandService.updateEmail(foundAccount.accountId(), verificationCode.newEmail());
        verificationCodeCommandService.delete(verificationCode.verificationCodeId());
        AccountActivityLog log = AccountActivityLog.builder()
                .accountId(currentAccountLogin.accountId())
                .actionCode(EAction.VERIFIED_EMAIL.getCode())
                .actionName(EAction.VERIFIED_EMAIL.getName())
                .build();
        accountActivityLogCommandService.save(log);

        return updatedAccount;
    }

    @Override
    public Account selfChangeInfo(Account account) {
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        Account updatedAccount = commandService.update(currentAccountLogin.accountId(), account);
        commonMediaCommandService.deleteAllByAccountId(updatedAccount.accountId());
        List<CommonMedia> commonMedia = commonMediaCommandService
                .saveAllForAccount(currentAccountLogin.accountId(), account.medias());

        return updatedAccount.withMedias(commonMedia);
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

    @Override
    public Account getAccountDetail(AccountId accountId) {
        Account account = queryService.findById(accountId.value());
        List<Role> roles = roleQueryService.findAllByAccountId(accountId.value());
        List<CommonMedia> commonMedia = commonMediaQueryService.findAllByAccountId(accountId.value());

        return account
                .withRoles(roles)
                .withMedias(commonMedia);
    }

    @Override
    public Account getSelfDetail() {
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        Account account = queryService.findById(currentAccountLogin.accountId());
        List<Role> roles = roleQueryService.findAllByAccountId(currentAccountLogin.accountId());
        List<CommonMedia> commonMedia = commonMediaQueryService.findAllByAccountId(currentAccountLogin.accountId());

        return account
                .withRoles(roles)
                .withMedias(commonMedia);
    }

    @Override
    public Page<Account> getAccountList(AccountFilter filter) {
        return queryService.findAll(filter.searchCriteria(), filter.pageRequestCustom());
    }

    @Override
    @Transactional
    public void deleteAccount(AccountId accountId) {
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        accountRoleCommandService.deleteByAccountId(accountId.value());
        commonMediaCommandService.deleteAllByAccountId(accountId.value());
        accountActivityLogCommandService.deleteAllByAccountId(accountId.value());
        commandService.delete(accountId.value());
        AccountActivityLog log = AccountActivityLog.builder()
                .accountId(currentAccountLogin.accountId())
                .actionCode(EAction.DELETE_ACCOUNT.getCode())
                .actionName(EAction.DELETE_ACCOUNT.getName())
                .build();
        accountActivityLogCommandService.save(log);
    }
}

package com.paw.fund.app.modules.account_management.service.account;

import com.paw.fund.app.modules.account_management.aspect.CreateAccountHelper;
import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.account.usecase.AccountSave;
import com.paw.fund.app.modules.account_management.domain.usecase.account.AccountEmail;
import com.paw.fund.app.modules.account_management.domain.usecase.account.AccountFilter;
import com.paw.fund.app.modules.account_management.domain.usecase.account.AccountId;
import com.paw.fund.app.modules.account_management.domain.usecase.account.AccountPassword;
import com.paw.fund.app.modules.account_management.domain.usecase.account.AccountRegisterRole;
import com.paw.fund.app.modules.account_management.domain.usecase.account.AccountUpdatePassword;
import com.paw.fund.app.modules.account_management.domain.usecase.account.AccountVerification;
import com.paw.fund.app.modules.account_management.service.account.usecase.IAccountUseCase;
import com.paw.fund.app.modules.account_management.service.account.role.AccountRoleCommandService;
import com.paw.fund.app.modules.log_management.annotation.CreateAccountActivityLogHelper;
import com.paw.fund.app.modules.log_management.domain.account.AccountActivityLog;
import com.paw.fund.app.modules.log_management.service.account.AccountActivityLogCommandService;
import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.media_management.service.common.CommonMediaCommandService;
import com.paw.fund.app.modules.media_management.service.common.CommonMediaQueryService;
import com.paw.fund.app.modules.account_management.domain.role.Role;
import com.paw.fund.app.modules.account_management.service.role.RoleQueryService;
import com.paw.fund.app.modules.verification_code_management.domain.VerificationCode;
import com.paw.fund.app.modules.verification_code_management.service.VerificationCodeCommandService;
import com.paw.fund.app.modules.verification_code_management.service.VerificationCodeQueryService;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.common.CurrentAccountLogin;
import com.paw.fund.enums.EAccountAction;
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
import org.springframework.web.filter.RequestContextFilter;

import java.util.List;

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
    private final RequestContextFilter requestContextFilter;

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
    @CreateAccountHelper
    @CreateAccountActivityLogHelper(action = EAccountAction.CREATED)
    public Account createAccount(AccountSave accountSave) {
        ValidationUtil.validateNotNullPointerException(accountSave);
        return commandService.save(accountSave.account());
    }

    @Override
    @Transactional
    @CreateAccountActivityLogHelper(action = EAccountAction.VERIFIED_ACCOUNT)
    public Account verifyCreatedAccount(AccountVerification accountVerification) {
        ValidationUtil.validateNotNullPointerException(accountVerification);
        Account account = queryService.findByAccountEmail(accountVerification.email());
        VerificationCode verificationCode = verificationCodeQueryService
                .findByCodeAndAccountIdAndVerificationCodeType(
                        accountVerification.verificationCode(),
                        account.accountId(),
                        EVerificationCodeType.ACCOUNT_CREATION);
        Account foundAccount = queryService.findById(verificationCode.accountId());
        Account updatedAccount = commandService.updateStatus(foundAccount.accountId(), EAccountStatus.ACTIVE);
        verificationCodeCommandService.delete(verificationCode.verificationCodeId());

        return updatedAccount;
    }

    @Override
    @CreateAccountActivityLogHelper(action = EAccountAction.VERIFIED_EMAIL, isCurrentLogin = true)
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

        return updatedAccount;
    }

    @Override
    @Transactional
    @CreateAccountActivityLogHelper(action = EAccountAction.SELF_UPDATE, isCurrentLogin = true)
    public Account selfChangeInfo(Account account) {
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        Account updatedAccount = commandService.update(currentAccountLogin.accountId(), account);
        commonMediaCommandService.deleteAllByAccountId(updatedAccount.accountId());
        List<CommonMedia> commonMedia = commonMediaCommandService
                .saveAllWithAccountId(currentAccountLogin.accountId(), account.medias());

        return updatedAccount.withMedias(commonMedia);
    }

    @Override
    @Transactional
    @CreateAccountActivityLogHelper(action = EAccountAction.ACTIVE_ACCOUNT, isCurrentLogin = true)
    public Account activeAccount(AccountId accountId) {
        ValidationUtil.validateNotNullPointerException(accountId);
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();

        return commandService.updateStatus(accountId.value(), EAccountStatus.ACTIVE);
    }

    @Override
    @Transactional
    @CreateAccountActivityLogHelper(action = EAccountAction.INACTIVE_ACCOUNT, isCurrentLogin = true)
    public Account inactiveAccount(AccountId accountId) {
        ValidationUtil.validateNotNullPointerException(accountId);
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();

        return commandService.updateStatus(accountId.value(), EAccountStatus.INACTIVE);
    }

    @Override
    @Transactional
    @CreateAccountActivityLogHelper(action = EAccountAction.SELF_CHANGE_PASS, isCurrentLogin = true)
    public Account selfChangePassword(AccountPassword accountPassword) {
        ValidationUtil.validateNotNullPointerException(accountPassword);
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        Account account = commandService.updatePassword(currentAccountLogin.accountId(), accountPassword.value());

        return account;
    }

    @Override
    public Account changePassword(AccountUpdatePassword accountUpdatePassword) {
        ValidationUtil.validateNotNullPointerException(accountUpdatePassword);
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        Account account = commandService.updatePassword(accountUpdatePassword.accountId(),
                accountUpdatePassword.password());
        AccountActivityLog log = AccountActivityLog.builder()
                .accountId(currentAccountLogin.accountId())
                .actionCode(EAccountAction.CHANGE_PASS.getCode())
                .actionName(EAccountAction.CHANGE_PASS.getName())
                .build();
        accountActivityLogCommandService.save(log);

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
    @CreateAccountActivityLogHelper(action = EAccountAction.DELETE_ACCOUNT, isCurrentLogin = true)
    public void deleteAccount(AccountId accountId) {
        accountRoleCommandService.deleteByAccountId(accountId.value());
        commonMediaCommandService.deleteAllByAccountId(accountId.value());
        accountActivityLogCommandService.deleteAllByAccountId(accountId.value());
        commandService.delete(accountId.value());
    }

    @Override
    @Transactional
    @CreateAccountActivityLogHelper(action = EAccountAction.REGISTER_ADOPTER, isCurrentLogin = true)
    public Account registerRole(AccountRegisterRole registerRole) {
        ValidationUtil.validateNotNullPointerException(registerRole);
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();
        Account account = commandService.update(currentAccountLogin.accountId(), registerRole.account());
        List<String> roleCodes = registerRole.roles().stream()
                .map(Role::roleCode)
                .toList();
        List<Role> foundRoles = roleQueryService.findAllByCodeIn(roleCodes);
        List<Long> roleIds = foundRoles.stream().map(Role::roleId).toList();
        accountRoleCommandService
                .saveAll(currentAccountLogin.accountId(), roleIds);

        return account.withRoles(foundRoles);
    }
}

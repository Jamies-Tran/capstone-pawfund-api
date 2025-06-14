package com.paw.fund.app.modules.account_management.service.account;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.account.AccountSave;
import com.paw.fund.app.modules.account_management.domain.account.role.event.listener.CreateAccountRoleEventListener;
import com.paw.fund.app.modules.account_management.domain.role.usecase.IRoleUseCase;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountEmail;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountFilter;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountId;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountPassword;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountRegisterRole;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountUpdatePassword;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountVerification;
import com.paw.fund.app.modules.account_management.domain.account.usecase.IAccountUseCase;
import com.paw.fund.app.modules.account_management.domain.role.usecase.data.transfer.RoleCodeList;
import com.paw.fund.app.modules.account_management.service.account.role.AccountRoleCommandService;
import com.paw.fund.app.modules.log_management.annotation.CreateAccountActivityLogHelper;
import com.paw.fund.app.modules.log_management.domain.account.AccountActivityLog;
import com.paw.fund.app.modules.log_management.service.account.AccountActivityLogCommandService;
import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.media_management.domain.common.event.listener.CreateCommonMediaListener;
import com.paw.fund.app.modules.media_management.domain.common.usecase.ICommonMediaUseCase;
import com.paw.fund.app.modules.media_management.service.common.CommonMediaCommandService;
import com.paw.fund.app.modules.media_management.service.common.CommonMediaQueryService;
import com.paw.fund.app.modules.account_management.domain.role.Role;
import com.paw.fund.app.modules.verification_management.domain.event.listener.DeleteVerificationEventListener;
import com.paw.fund.common.aspect.annotation.validate.args.ValidateArgs;
import com.paw.fund.common.context.request.RequestContext;
import com.paw.fund.enums.EAccountAction;
import com.paw.fund.enums.EAccountStatus;
import com.paw.fund.enums.EVerificationType;
import com.paw.fund.utils.CollectionUtils;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    IRoleUseCase roleUseCase;

    @NonNull
    ICommonMediaUseCase commonMediaUseCase;

    @NonNull
    AccountActivityLogCommandService accountActivityLogCommandService;

    @NonNull
    ApplicationEventPublisher publisher;

    @Override
    @ValidateArgs
    public Account getAccount(AccountId accountId) {
        return queryService.findById(accountId.value());
    }

    @Override
    @ValidateArgs
    public Account getAccountForAuth(AccountEmail accountEmail) {
        Account foundAccount = queryService.findByAccountEmail(accountEmail.value());
        List<Role> roles = roleUseCase.getRoleByAccountId(AccountId.of(foundAccount.accountId()));

        return foundAccount.withRoles(roles);
    }

    @Override
    @Transactional
    @ValidateArgs
    public Account createAccount(AccountSave accountSave) {
        Account newAccount = accountSave.account();
        Account account = commandService.save(newAccount);

        createRole(account.accountId(), newAccount.roles());
        createMedia(account.accountId(), newAccount.medias());

        return account;
    }

    @Async
    protected void createRole(Long accountId, List<Role> roles) {
        if(CollectionUtils.hasElement(roles)) {
            List<Long> roleIds = roleUseCase
                    .getRoleInCodeList(RoleCodeList.of(roles.stream().map(Role::roleCode).toList()))
                    .stream()
                    .map(Role::roleId)
                    .toList();
            publisher.publishEvent(new CreateAccountRoleEventListener(this, accountId, roleIds));
        }
    }

    @Async
    protected void createMedia(Long accountId, List<CommonMedia> medias) {
        if(CollectionUtils.hasElement(medias)) {
            publisher.publishEvent(new CreateCommonMediaListener(this, accountId, medias));
        }
    }


    @Override
    @Transactional
    @ValidateArgs
    public Account verifyCreatedAccount(AccountVerification accountVerification) {
        String verificationCode = accountVerification.verificationCode();
        Account foundAccount = queryService.findByVerificationCodeAndVerifyType(
                verificationCode,
                EVerificationType.ACCOUNT_CREATION
        );
        Account updatedAccount = commandService.updateStatus(foundAccount.accountId(), EAccountStatus.ACTIVE);
        deleteVerification(verificationCode);

        return updatedAccount;
    }

    @Async
    protected void deleteVerification(String verificationId) {
        publisher.publishEvent(new DeleteVerificationEventListener(
                this,
                verificationId
        ));
    }

    @Override
    @Transactional
    @ValidateArgs
    public Account verifyNewEmail(AccountVerification accountVerification) {
        Account currentAccountLogin = getCurrentAccountLogin();
        Account updatedAccount = commandService.updateEmailByAccountIdAndVerificationCode(
                currentAccountLogin.accountId(),
                accountVerification.verificationCode()
        );
        deleteVerification(accountVerification.verificationCode());

        return updatedAccount;
    }

    @Override
    @Transactional
    @CreateAccountActivityLogHelper(action = EAccountAction.SELF_UPDATE, isCurrentLogin = true)
    public Account selfChangeInfo(Account account) {
        Account currentAccountLogin = getCurrentAccountLogin();
        Account updatedAccount = commandService.update(currentAccountLogin.accountId(), account);
        publisher.publishEvent(new CreateCommonMediaListener(
                this,
                currentAccountLogin.accountId(),
                account.medias()
        ));

        return updatedAccount;
    }

    @Override
    @Transactional
    @CreateAccountActivityLogHelper(action = EAccountAction.ACTIVE_ACCOUNT, isCurrentLogin = true)
    public Account activeAccount(AccountId accountId) {
        ValidationUtil.validateNotNullPointerException(accountId);
        Account currentAccountLogin = getCurrentAccountLogin();

        return commandService.updateStatus(accountId.value(), EAccountStatus.ACTIVE);
    }

    @Override
    @Transactional
    @CreateAccountActivityLogHelper(action = EAccountAction.INACTIVE_ACCOUNT, isCurrentLogin = true)
    public Account inactiveAccount(AccountId accountId) {
        ValidationUtil.validateNotNullPointerException(accountId);

        return commandService.updateStatus(accountId.value(), EAccountStatus.INACTIVE);
    }

    @Override
    @Transactional
    @CreateAccountActivityLogHelper(action = EAccountAction.SELF_CHANGE_PASS, isCurrentLogin = true)
    public Account selfChangePassword(AccountPassword accountPassword) {
        ValidationUtil.validateNotNullPointerException(accountPassword);
        Account currentAccountLogin = getCurrentAccountLogin();

        return commandService.updatePassword(currentAccountLogin.accountId(), accountPassword.value());
    }

    @Override
    public Account changePassword(AccountUpdatePassword accountUpdatePassword) {
        ValidationUtil.validateNotNullPointerException(accountUpdatePassword);
        Account currentAccountLogin = getCurrentAccountLogin();
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
        List<Role> roles = roleUseCase.getRoleByAccountId(AccountId.of(accountId.value()));
        List<CommonMedia> commonMedia = commonMediaUseCase.getCommonMediaListByAccountId(accountId);

        return account
                .withRoles(roles)
                .withMedias(commonMedia);
    }

    @Override
    public Account getSelfDetail() {
        Account currentAccountLogin = getCurrentAccountLogin();
        Account account = queryService.findById(currentAccountLogin.accountId());
        List<Role> roles = roleUseCase.getRoleByAccountId(AccountId.of(account.accountId()));
        List<CommonMedia> commonMedia = commonMediaUseCase.getCommonMediaListByAccountId(AccountId.of(account.accountId()));

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
        accountActivityLogCommandService.deleteAllByAccountId(accountId.value());
        commandService.delete(accountId.value());
    }

    @Override
    @Transactional
    @CreateAccountActivityLogHelper(action = EAccountAction.REGISTER_ADOPTER, isCurrentLogin = true)
    public Account registerRole(AccountRegisterRole registerRole) {
        ValidationUtil.validateNotNullPointerException(registerRole);
        Account currentAccountLogin = getCurrentAccountLogin();
        Account account = commandService.update(currentAccountLogin.accountId(), registerRole.account());
        List<String> roleCodes = registerRole.roles().stream()
                .map(Role::roleCode)
                .toList();
        List<Role> foundRoles = roleUseCase.getRoleInCodeList(RoleCodeList.of(roleCodes));

        return account.withRoles(foundRoles);
    }

    @Override
    public Account tryToGetAccountByEmail(AccountEmail accountEmail) {
        return queryService.findByAccountEmailNullable(accountEmail.value())
                .orElse(null);
    }

    @Override
    public Account getAccountByEmail(AccountEmail accountEmail) {
        return queryService.findByAccountEmail(accountEmail.value());
    }

    private Account getCurrentAccountLogin() {
        return queryService.findByAccountEmail(RequestContext.getCurrentAccountLogin());
    }
}

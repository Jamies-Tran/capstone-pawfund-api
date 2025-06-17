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
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.CurrentAccountLogin;
import com.paw.fund.app.modules.login_info_management.repository.database.ILoginInfoUseCase;
import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.media_management.domain.common.event.listener.CreateCommonMediaListener;
import com.paw.fund.app.modules.media_management.domain.common.event.listener.UpdateCommonMediaListener;
import com.paw.fund.app.modules.media_management.domain.common.usecase.ICommonMediaUseCase;
import com.paw.fund.app.modules.account_management.domain.role.Role;
import com.paw.fund.app.modules.verification_management.domain.event.listener.DeleteVerificationEventListener;
import com.paw.fund.common.aspect.annotation.validate.args.ValidateArgs;
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
    IRoleUseCase roleUseCase;

    @NonNull
    ICommonMediaUseCase commonMediaUseCase;

    @NonNull
    ILoginInfoUseCase loginInfoUseCase;

    @NonNull
    ApplicationEventPublisher publisher;

    @Override
    @ValidateArgs
    @Transactional(readOnly = true)
    public Account getAccountForAuth(AccountEmail accountEmail) {
        Account foundAccount = queryService.findByAccountEmail(accountEmail.value());
        List<Role> roles = getRoleByAccountId(foundAccount.accountId());

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
        CurrentAccountLogin currentAccountLogin = getCurrentAccountLogin();
        Account updatedAccount = commandService.updateEmailByAccountIdAndVerificationCode(
                currentAccountLogin.accountId(),
                accountVerification.verificationCode()
        );
        deleteVerification(accountVerification.verificationCode());

        return updatedAccount;
    }

    @Override
    @Transactional
    public Account selfChangeInfo(Account account) {
        CurrentAccountLogin currentAccountLogin = getCurrentAccountLogin();
        Account updatedAccount = commandService.update(currentAccountLogin.accountId(), account);
        publisher.publishEvent(new UpdateCommonMediaListener(
                this,
                currentAccountLogin.accountId(),
                account.medias()
        ));

        return updatedAccount;
    }

    @Override
    @Transactional
    @ValidateArgs
    public Account activeAccount(AccountId accountId) {
        return commandService.updateStatus(accountId.value(), EAccountStatus.ACTIVE);
    }

    @Override
    @Transactional
    @ValidateArgs
    public Account inactiveAccount(AccountId accountId) {
        return commandService.updateStatus(accountId.value(), EAccountStatus.INACTIVE);
    }

    @Override
    @Transactional
    @ValidateArgs
    public Account selfChangePassword(AccountPassword accountPassword) {
        CurrentAccountLogin currentAccountLogin = getCurrentAccountLogin();

        return commandService.updatePassword(currentAccountLogin.accountId(), accountPassword.value());
    }

    @Override
    @Transactional
    @ValidateArgs
    public Account changePassword(AccountUpdatePassword accountUpdatePassword) {
        return commandService.updatePassword(accountUpdatePassword.accountId(),
                accountUpdatePassword.password());
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccountDetail(AccountId accountId) {
        Account account = queryService.findById(accountId.value());
        List<Role> roles = getRoleByAccountId(accountId.value());
        List<CommonMedia> commonMedia = getCommonMediaByAccountId(accountId.value());

        return account
                .withRoles(roles)
                .withMedias(commonMedia);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getSelfDetail() {
        CurrentAccountLogin currentAccountLogin = getCurrentAccountLogin();
        Account account = queryService.findById(currentAccountLogin.accountId());
        List<Role> roles = getRoleByAccountId(currentAccountLogin.accountId());
        List<CommonMedia> commonMedia = getCommonMediaByAccountId(currentAccountLogin.accountId());

        return account
                .withRoles(roles)
                .withMedias(commonMedia);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Account> getAccountList(AccountFilter filter) {
        return queryService.findAll(filter.searchCriteria(), filter.pageRequestCustom());
    }

    @Override
    @Transactional
    public void deleteAccount(AccountId accountId) {
        commandService.delete(accountId.value());
    }

    @Override
    @Transactional
    @ValidateArgs
    public Account registerRole(AccountRegisterRole registerRole) {
        CurrentAccountLogin currentAccountLogin = getCurrentAccountLogin();
        Account account = commandService.update(currentAccountLogin.accountId(), registerRole.account());
        List<Long> roleIds = getRoleInCodeList(registerRole.roles().stream().map(Role::roleCode).toList())
                .stream()
                .map(Role::roleId)
                .toList();
        publisher.publishEvent(new CreateAccountRoleEventListener(
                this,
                account.accountId(),
                roleIds
        ));

        return account;
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccountByEmail(AccountEmail accountEmail) {
        return queryService.findByAccountEmail(accountEmail.value());
    }

    private CurrentAccountLogin getCurrentAccountLogin() {
        return loginInfoUseCase.getCurrentAccountLogin();
    }
    
    private List<Role> getRoleByAccountId(Long accountId) {
        return roleUseCase.getRoleByAccountId(AccountId.of(accountId));
    }

    private List<Role> getRoleInCodeList(List<String> roleCodes) {
        return roleUseCase.getRoleInCodeList(RoleCodeList.of(roleCodes));
    }
    
    private List<CommonMedia> getCommonMediaByAccountId(Long accountId) {
        return commonMediaUseCase.getCommonMediaListByAccountId(AccountId.of(accountId));
    }
}

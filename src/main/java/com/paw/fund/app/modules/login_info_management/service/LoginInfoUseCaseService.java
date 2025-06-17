package com.paw.fund.app.modules.login_info_management.service;

import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountId;
import com.paw.fund.app.modules.account_management.domain.role.Role;
import com.paw.fund.app.modules.account_management.domain.role.usecase.IRoleUseCase;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.Account;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.CurrentAccountLogin;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.Login;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.LoginInfo;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.RefreshToken;
import com.paw.fund.app.modules.login_info_management.repository.database.ILoginInfoUseCase;
import com.paw.fund.common.aspect.annotation.validate.args.ValidateArgs;
import com.paw.fund.common.context.request.RequestContext;
import com.paw.fund.configuration.handler.exceptions.AuthenticationException;
import com.paw.fund.enums.EAccountStatus;
import com.paw.fund.enums.ELoginStatus;
import com.paw.fund.utils.password.encoder.PawFundPasswordEncoder;
import com.paw.fund.utils.token.TokenUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginInfoUseCaseService implements ILoginInfoUseCase {
    @NonNull
    LoginInfoCommandService commandService;

    @NonNull
    LoginInfoQueryService queryService;

    @NonNull
    PawFundPasswordEncoder appPasswordEncoder;

    @NonNull
    IRoleUseCase roleUseCase;

    @NonNull
    TokenUtil tokenUtil;

    @Override
    @Transactional
    @ValidateArgs
    public Login login(LoginInfo login) {
        Account account = queryService.findAccountByEmail(login.email());
        List<Role> roles = getRoleByAccountId(account.accountId());

        validateLogin(account, login);

        String accessToken = tokenUtil.generateAccessToken(account.email());
        String refreshToken = UUID.randomUUID().toString();
        LocalDateTime accessExpiredAt = tokenUtil.accessTokenExpiredAt().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        LocalDateTime refreshExpiredAt = tokenUtil.refreshTokenExpiredAt().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        Login newLogin = Login.builder()
                .accountId(account.accountId())
                .accountEmail(account.email())
                .latitude(login.latitude())
                .longitude(login.longitude())
                .refreshToken(refreshToken)
                .refreshExpiredAt(refreshExpiredAt)
                .accessExpiredAt(accessExpiredAt)
                .statusCode(ELoginStatus.LOGIN.getCode())
                .statusName(ELoginStatus.LOGIN.getName())
                .build();

        Login loginInfo = commandService.save(newLogin);

        return loginInfo
                .withAccessToken(accessToken)
                .withAccount(account.withRoles(roles));
    }

    private void validateLogin(Account account, LoginInfo login) {
        if(!Objects.equals(account.statusCode(), EAccountStatus.ACTIVE.getCode())) {
            throw new AuthenticationException("Tài khoản chưa được kích hoạt");
        }

        if(!appPasswordEncoder.bCryptpasswordEncoder().matches(login.password(), account.password())) {
            throw new AuthenticationException();
        }
    }

    @Override
    @Transactional
    public Login refresh(RefreshToken refreshToken) {
        LocalDateTime accessExpiredAt = tokenUtil.accessTokenExpiredAt().toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        Login login = commandService.updateByRefreshToken(refreshToken.value(), accessExpiredAt);
        Account account = queryService.findAccountByEmail(login.accountEmail());
        String accessToken = tokenUtil.generateAccessToken(account.email());

        return login
                .withAccessToken(accessToken)
                .withAccount(account);
    }

    @Override
    @Transactional
    public void logout() {
        Account account = queryService.findAccountByEmail(RequestContext.getCurrentAccountLogin());
        Login newLogin = Login.builder()
                .accountId(account.accountId())
                .accountEmail(account.email())
                .statusCode(ELoginStatus.LOGOUT.getCode())
                .statusName(ELoginStatus.LOGOUT.getName())
                .build();
        commandService.save(newLogin);
    }

    @Override
    @Transactional(readOnly = true)
    public CurrentAccountLogin getCurrentAccountLogin() {
        CurrentAccountLogin currentAccountLogin = queryService.findCurrentAccountLogin();
        List<Role> roles = getRoleByAccountId(currentAccountLogin.accountId());

        return currentAccountLogin.withRoles(roles);
    }

    @Override
    @Transactional(readOnly = true)
    public Login getCurrentLoginInfo(String email) {
        return queryService.findTopOrderByCreatedAtDesc(email);
    }

    private List<Role> getRoleByAccountId(Long accountId) {
        return roleUseCase.getRoleByAccountId(AccountId.of(accountId));
    }
}

package com.paw.fund.app.modules.login_info_management.repository.database;

import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.CurrentAccountLogin;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.Login;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.LoginInfo;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.RefreshToken;

public interface ILoginInfoUseCase {
    Login login(com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.LoginInfo login);

    Login refresh(RefreshToken refreshToken);

    void logout();

    CurrentAccountLogin getCurrentAccountLogin();

    Login getCurrentLoginInfo(String email);
}

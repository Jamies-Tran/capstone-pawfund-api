package com.paw.fund.app.modules.session_management.service.usecase;

import com.paw.fund.app.modules.session_management.domain.Session;
import com.paw.fund.app.modules.session_management.domain.usecase.LoginInfo;
import com.paw.fund.app.modules.session_management.domain.usecase.RefreshToken;

public interface ISessionUseCase {
    Session login(LoginInfo login);

    Session refresh(RefreshToken refreshToken);

    void logout();
}

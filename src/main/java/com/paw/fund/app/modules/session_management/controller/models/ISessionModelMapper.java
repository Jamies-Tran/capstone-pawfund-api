package com.paw.fund.app.modules.session_management.controller.models;

import com.paw.fund.app.modules.session_management.domain.Session;
import com.paw.fund.app.modules.session_management.domain.usecase.LoginInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISessionModelMapper {
    LoginInfo toDto(LoginRequest request);

    SessionResponse toResponse(Session dto);

    RefreshResponse toRefreshResponse(Session dto);
}

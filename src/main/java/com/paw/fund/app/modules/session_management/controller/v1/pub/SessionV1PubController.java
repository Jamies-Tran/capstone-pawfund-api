package com.paw.fund.app.modules.session_management.controller.v1.pub;

import com.paw.fund.app.modules.session_management.controller.models.ISessionModelMapper;
import com.paw.fund.app.modules.session_management.controller.models.LoginRequest;
import com.paw.fund.app.modules.session_management.controller.models.RefreshResponse;
import com.paw.fund.app.modules.session_management.controller.models.SessionResponse;
import com.paw.fund.app.modules.session_management.domain.Session;
import com.paw.fund.app.modules.session_management.domain.usecase.LoginInfo;
import com.paw.fund.app.modules.session_management.domain.usecase.RefreshToken;
import com.paw.fund.app.modules.session_management.service.usecase.ISessionUseCase;
import com.paw.fund.utils.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SessionV1PubController implements ISessionV1PubAPI {
    @NonNull
    ISessionUseCase useCase;

    @NonNull
    ISessionModelMapper modelMapper;

    @Value("${app.version}")
    @NonFinal
    String API_VERSION;

    @Override
    public ValueResponse<SessionResponse> login(LoginRequest request) {
        LoginInfo loginInfo = modelMapper.toDto(request);
        Session session = useCase.login(loginInfo);

        return ValueResponse.success(modelMapper.toResponse(session), HttpStatus.CREATED, API_VERSION);
    }

    @Override
    public ValueResponse<RefreshResponse> refresh(String refreshToken) {
        Session session = useCase.refresh(RefreshToken.of(refreshToken));

        return ValueResponse.success(modelMapper.toRefreshResponse(session), HttpStatus.OK, API_VERSION);
    }
}

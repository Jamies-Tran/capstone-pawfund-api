package com.paw.fund.app.modules.login_info_management.controller.v1.pub;

import com.paw.fund.app.modules.login_info_management.controller.models.mapper.ILoginInfoModelMapper;
import com.paw.fund.app.modules.login_info_management.controller.models.LoginInfoResponse;
import com.paw.fund.app.modules.login_info_management.controller.models.LoginRequest;
import com.paw.fund.app.modules.login_info_management.controller.models.RefreshResponse;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.Login;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.RefreshToken;
import com.paw.fund.app.modules.login_info_management.repository.database.ILoginInfoUseCase;
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
public class LoginInfoV1PubController implements ILoginInfoV1PubAPI {
    @NonNull
    ILoginInfoUseCase useCase;

    @NonNull
    ILoginInfoModelMapper modelMapper;

    @Value("${app.version}")
    @NonFinal
    String API_VERSION;

    @Override
    public ValueResponse<LoginInfoResponse> login(LoginRequest request) {
        com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.LoginInfo loginInfo = modelMapper.toDto(request);
        Login session = useCase.login(loginInfo);

        return ValueResponse.success(modelMapper.toResponse(session), HttpStatus.CREATED);
    }

    @Override
    public ValueResponse<RefreshResponse> refresh(String refreshToken) {
        Login login = useCase.refresh(RefreshToken.of(refreshToken));

        return ValueResponse.success(modelMapper.toRefreshResponse(login), HttpStatus.OK);
    }
}

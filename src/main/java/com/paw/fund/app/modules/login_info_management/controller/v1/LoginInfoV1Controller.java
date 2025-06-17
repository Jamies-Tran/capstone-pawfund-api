package com.paw.fund.app.modules.login_info_management.controller.v1;

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
public class LoginInfoV1Controller implements ILoginInfoV1API {
    @NonNull
    ILoginInfoUseCase useCase;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<?> logout() {
        useCase.logout();

        return ValueResponse.success("", HttpStatus.OK );
    }
}

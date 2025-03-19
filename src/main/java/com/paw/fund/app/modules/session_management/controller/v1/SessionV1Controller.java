package com.paw.fund.app.modules.session_management.controller.v1;

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
public class SessionV1Controller implements ISessionV1API {
    @NonNull
    ISessionUseCase useCase;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<?> logout() {
        useCase.logout();

        return ValueResponse.success("", HttpStatus.OK, API_VERSION );
    }
}

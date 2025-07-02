package com.paw.fund.app.modules.login_info_management.service;


import com.paw.fund.app.modules.login_info_management.domain.ILoginInfoMapper;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.Login;
import com.paw.fund.app.modules.login_info_management.repository.database.ILoginInfoRepository;
import com.paw.fund.app.modules.login_info_management.repository.database.LoginInfoEntity;
import com.paw.fund.common.aspect.annotation.validate.args.ValidateArgs;
import com.paw.fund.common.context.request.RequestContext;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.enums.ELoginStatus;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginInfoCommandService {
    @NonNull
    ILoginInfoRepository repository;

    @NonNull
    ILoginInfoMapper mapper;

    @ValidateArgs
    protected Login save(Login login) {
        LoginInfoEntity newSession = mapper.toEntity(login);
        LoginInfoEntity savedSession = repository.save(newSession);

        return mapper.toDto(savedSession);
    }

    @ValidateArgs
    protected Login updateByRefreshToken(String refreshToken, LocalDateTime accessExpiredAt) {
        LoginInfoEntity foundLogin = repository.findByRefreshToken(refreshToken)
                .orElseThrow(ResourceNotFoundException::new);
        if(LocalDateTime.now().isAfter(foundLogin.getRefreshExpiredAt())) {
            throw new ResourceNotValidException("Token đã hết hạn");
        }
        foundLogin.setAccessExpiredAt(accessExpiredAt);
        LoginInfoEntity savedSession = repository.save(foundLogin);

        return mapper.toDto(savedSession);
    }

    protected void delete() {
        String email = RequestContext.getCurrentAccountLogin();
        LoginInfoEntity loginInfo = repository
                .findByAccountEmail(email)
                .orElseThrow(ResourceNotFoundException::new);
        repository.delete(loginInfo);
    }

}

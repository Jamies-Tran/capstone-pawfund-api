package com.paw.fund.app.modules.verification_code_management.controller.v1;

import com.paw.fund.app.modules.verification_code_management.controller.models.IVerificationCodeModelMapper;
import com.paw.fund.app.modules.verification_code_management.controller.models.VerificationEmailRequest;
import com.paw.fund.app.modules.verification_code_management.controller.models.VerificationCodeResponse;
import com.paw.fund.app.modules.verification_code_management.domain.VerificationCode;
import com.paw.fund.app.modules.verification_code_management.domain.usecase.VerificationMail;
import com.paw.fund.app.modules.verification_code_management.service.usecase.IVerificationCodeUseCase;
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
public class VerificationCodeV1Controller implements IVerificationCodeV1API {

    @NonNull
    IVerificationCodeUseCase useCase;

    @NonNull
    IVerificationCodeModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<VerificationCodeResponse> sendEmailVerification(VerificationEmailRequest verificationCodeRequest) {
        VerificationCode verificationCode = useCase
                .createAndSendCodeVerificationEmail(VerificationMail.of(verificationCodeRequest.email()));

        return ValueResponse.success(modelMapper.toResponse(verificationCode), HttpStatus.OK, API_VERSION);
    }
}

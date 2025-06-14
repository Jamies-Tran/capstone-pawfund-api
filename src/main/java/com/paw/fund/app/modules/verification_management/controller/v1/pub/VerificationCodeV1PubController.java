package com.paw.fund.app.modules.verification_management.controller.v1.pub;

import com.paw.fund.app.modules.verification_management.controller.models.IVerificationCodeModelMapper;
import com.paw.fund.app.modules.verification_management.controller.models.VerificationEmailRequest;
import com.paw.fund.app.modules.verification_management.controller.models.VerificationCodeResponse;
import com.paw.fund.app.modules.verification_management.domain.Verification;
import com.paw.fund.app.modules.verification_management.domain.usecase.data.transfer.VerificationMail;
import com.paw.fund.app.modules.verification_management.domain.usecase.IVerificationUseCase;
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
public class VerificationCodeV1PubController implements IVerificationCodeV1PubAPI {
    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @NonNull
    IVerificationUseCase useCase;

    @NonNull
    IVerificationCodeModelMapper modelMapper;

    @Override
    public ValueResponse<VerificationCodeResponse> sendAccountVerification(VerificationEmailRequest verificationCodeRequest) {
        Verification verification = useCase
                .createAndSendCodeVerificationAccount(VerificationMail.of(verificationCodeRequest.email()));

        return ValueResponse.success(modelMapper.toResponse(verification), HttpStatus.OK);
    }
}

package com.paw.fund.app.modules.account_management.controller.v1.pub;

import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.IAccountModelMapper;
import com.paw.fund.app.modules.account_management.controller.models.verification.code.VerificationCodeRequest;
import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountId;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountVerification;
import com.paw.fund.app.modules.account_management.service.usecase.IAccountUseCase;
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
public class AccountPathV1PubController implements IAccountPathV1PubAPI {
    @NonFinal
    @Value("${app.version}")
    String APP_VERSION;

    @NonNull
    IAccountUseCase useCase;

    @NonNull
    IAccountModelMapper modelMapper;


    @Override
    public ValueResponse<AccountResponse> verifyCreatedAccount(Long accountId, VerificationCodeRequest verificationCode) {
        AccountVerification accountVerification = AccountVerification.of(accountId, verificationCode.verificationCode());
        Account verifiedAccount = useCase.verifyCreatedAccount(accountVerification);

        return ValueResponse.success(modelMapper.toResponse(verifiedAccount), HttpStatus.OK, APP_VERSION);
    }

    @Override
    public ValueResponse<AccountResponse> getAccountDetail(Long accountId) {
        Account account = useCase.getAccountDetail(AccountId.of(accountId));

        return ValueResponse.success(modelMapper.toResponse(account), HttpStatus.OK, APP_VERSION);
    }
}

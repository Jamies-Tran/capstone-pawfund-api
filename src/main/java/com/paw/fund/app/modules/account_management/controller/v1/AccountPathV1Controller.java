package com.paw.fund.app.modules.account_management.controller.v1;

import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.AccountUpdatePasswordRequest;
import com.paw.fund.app.modules.account_management.controller.models.IAccountModelMapper;
import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountId;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountUpdatePassword;
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
public class AccountPathV1Controller implements IAccountPathV1API {
    @NonNull
    IAccountUseCase useCase;

    @NonNull
    IAccountModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<AccountResponse> activeAccount(Long accountId) {
        Account account = useCase.activeAccount(AccountId.of(accountId));

        return ValueResponse.success(modelMapper.toResponse(account), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<AccountResponse> inActiveAccount(Long accountId) {
        Account account = useCase.inactiveAccount(AccountId.of(accountId));

        return ValueResponse.success(modelMapper.toResponse(account), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<AccountResponse> changePassword(Long accountId, AccountUpdatePasswordRequest accountUpdatePasswordRequest) {
        AccountUpdatePassword accountUpdatePassword = AccountUpdatePassword.of(accountId,
                accountUpdatePasswordRequest.password());
        Account account = useCase.changePassword(accountUpdatePassword);

        return ValueResponse.success(modelMapper.toResponse(account), HttpStatus.OK, API_VERSION);
    }
}

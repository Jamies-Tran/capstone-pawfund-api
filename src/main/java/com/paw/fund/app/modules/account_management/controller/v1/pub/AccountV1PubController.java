package com.paw.fund.app.modules.account_management.controller.v1.pub;

import com.paw.fund.app.modules.account_management.controller.models.AccountRequest;
import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.IAccountModelMapper;
import com.paw.fund.app.modules.account_management.domain.Account;
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
public class AccountV1PubController implements IAccountV1PubAPI {
    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @NonNull
    IAccountUseCase useCase;

    @NonNull
    IAccountModelMapper modelMapper;

    @Override
    public ValueResponse<AccountResponse> createAccount(AccountRequest accountRequest) {
        Account account = modelMapper.toDto(accountRequest);
        Account createdAccount = useCase.createAccount(account);

        return ValueResponse.success(modelMapper.toResponse(createdAccount), HttpStatus.CREATED, API_VERSION);
    }
}

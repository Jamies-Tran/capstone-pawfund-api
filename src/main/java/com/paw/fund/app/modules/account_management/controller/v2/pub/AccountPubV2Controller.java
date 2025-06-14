package com.paw.fund.app.modules.account_management.controller.v2.pub;

import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.AccountV2Request;
import com.paw.fund.app.modules.account_management.controller.models.IAccountModelMapper;
import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.account.AccountSave;
import com.paw.fund.app.modules.account_management.domain.account.usecase.IAccountUseCase;
import com.paw.fund.app.modules.account_management.domain.role.usecase.IRoleUseCase;
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
public class AccountPubV2Controller implements IAccountPubV2API {
    @NonFinal
    @Value("${app.version}")
    String APP_VERSION;

    @NonNull
    IAccountUseCase useCase;

    @NonNull
    IRoleUseCase roleUseCase;

    @NonNull
    IAccountModelMapper modelMapper;


    @Override
    public ValueResponse<AccountResponse> createAccount(AccountV2Request request) {
        Account newAccount = modelMapper.toDto(request);
        Account savedAccount = useCase.createAccount(AccountSave.of(newAccount));

        return ValueResponse.success(
                modelMapper.toResponse(savedAccount),
                HttpStatus.CREATED
        );
    }
}

package com.paw.fund.app.modules.account_management.controller.v2.pub;

import com.paw.fund.app.modules.account_management.controller.models.AccountRequest;
import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.IAccountModelMapper;
import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.service.usecase.IAccountUseCase;
import com.paw.fund.app.modules.role_management.domain.Role;
import com.paw.fund.app.modules.role_management.service.usecase.IRoleUseCase;
import com.paw.fund.utils.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ValueResponse<AccountResponse> createAdopterAndDonor(AccountRequest request) {
        List<Role> roles = List.of(roleUseCase.getAdaptorRole(), roleUseCase.getDonorRole());
        Account newAccount = modelMapper.toDto(request, roles);
        Account savedAccount = useCase.createAccount(newAccount);

        return ValueResponse.success(
                modelMapper.toResponse(savedAccount),
                HttpStatus.CREATED,
                APP_VERSION
        );
    }
}

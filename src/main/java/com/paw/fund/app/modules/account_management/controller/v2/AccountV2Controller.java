package com.paw.fund.app.modules.account_management.controller.v2;

import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.AccountRoleRegisterRequest;
import com.paw.fund.app.modules.account_management.controller.models.IAccountModelMapper;
import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountRegisterRole;
import com.paw.fund.app.modules.account_management.service.usecase.IAccountUseCase;
import com.paw.fund.enums.ERole;
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
public class AccountV2Controller implements IAccountV2API {
    @NonNull
    IAccountUseCase useCase;

    @NonNull
    IAccountModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<AccountResponse> registerAdopterRole(AccountRoleRegisterRequest request) {
        AccountRegisterRole accountRegisterRole = AccountRegisterRole.of(modelMapper.toDto(request),
                List.of(ERole.ADOPTER));
        Account registeredAccount = useCase.registerRole(accountRegisterRole);

        return ValueResponse.success(modelMapper.toResponse(registeredAccount), HttpStatus.OK, API_VERSION);
    }
}

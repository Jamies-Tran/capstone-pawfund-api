package com.paw.fund.app.modules.account_management.controller.v2;

import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.AccountRoleRegisterRequest;
import com.paw.fund.app.modules.account_management.controller.models.IAccountModelMapper;
import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountRegisterRole;
import com.paw.fund.app.modules.account_management.domain.account.usecase.IAccountUseCase;
import com.paw.fund.enums.ERole;
import com.paw.fund.utils.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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

    @Override
    public ValueResponse<AccountResponse> registerAdopterRole(AccountRoleRegisterRequest request) {
        AccountRegisterRole accountRegisterRole = AccountRegisterRole.of(modelMapper.toDto(request),
                List.of(ERole.ADOPTER));
        Account registeredAccount = useCase.registerRole(accountRegisterRole);

        return ValueResponse.success(modelMapper.toResponse(registeredAccount), HttpStatus.OK);
    }
}

package com.paw.fund.app.modules.account_management.controller.v1;

import com.paw.fund.app.modules.account_management.controller.models.AccountRequest;
import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.AccountUpdatePasswordRequest;
import com.paw.fund.app.modules.account_management.controller.models.AccountUpdateRequest;
import com.paw.fund.app.modules.account_management.controller.models.IAccountModelMapper;
import com.paw.fund.app.modules.account_management.controller.models.verification.code.VerificationCodeRequest;
import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountPassword;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountVerification;
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
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class AccountV1Controller implements IAccountV1API {

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @NonNull
    IAccountUseCase useCase;

    @NonNull
    IRoleUseCase roleUseCase;

    @NonNull
    IAccountModelMapper modelMapper;

    @Override
    public ValueResponse<AccountResponse> createAdmin(AccountRequest accountRequest) {
        List<Role> roles = List.of(roleUseCase.getAdminRole());
        Account account = modelMapper.toDto(accountRequest, roles);
        Account createdAccount = useCase.createAccount(account);

        return ValueResponse.success(modelMapper.toResponse(createdAccount), HttpStatus.CREATED, API_VERSION);
    }

    @Override
    public ValueResponse<AccountResponse> createStaff(AccountRequest accountRequest) {
        List<Role> roles = List.of(roleUseCase.getStaffRole());
        Account account = modelMapper.toDto(accountRequest, roles);
        Account createdAccount = useCase.createAccount(account);

        return ValueResponse.success(modelMapper.toResponse(createdAccount), HttpStatus.CREATED, API_VERSION);
    }

    @Override
    public ValueResponse<AccountResponse> selfChangeInfo(AccountUpdateRequest updateRequest) {
        Account updateAccount = modelMapper.toDto(updateRequest);
        Account updatedAccount = useCase.selfChangeInfo(updateAccount);

        return ValueResponse.success(modelMapper.toResponse(updatedAccount), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<AccountResponse> selfChangePassword(AccountUpdatePasswordRequest accountUpdatePasswordRequest) {
        AccountPassword accountPassword = AccountPassword.of(accountUpdatePasswordRequest.password());
        Account account = useCase.selfChangePassword(accountPassword);

        return ValueResponse.success(modelMapper.toResponse(account), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<AccountResponse> verifyEmail(VerificationCodeRequest emailVerifyCodeRequest) {
        Account account = useCase.verifyNewEmail(AccountVerification.of(emailVerifyCodeRequest.verificationCode()));

        return ValueResponse.success(modelMapper.toResponse(account), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<AccountResponse> getSelfAccountDetail() {
        Account account = useCase.getSelfDetail();

        return ValueResponse.success(modelMapper.toResponse(account), HttpStatus.OK, API_VERSION);
    }
}

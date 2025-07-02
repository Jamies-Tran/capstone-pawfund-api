package com.paw.fund.app.modules.account_management.controller.v1;

import com.paw.fund.app.modules.account_management.controller.models.AccountRequest;
import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.AccountUpdatePasswordRequest;
import com.paw.fund.app.modules.account_management.controller.models.IAccountModelMapper;
import com.paw.fund.app.modules.account_management.controller.models.verification.code.EmailVerificationCodeRequest;
import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.account.AccountSave;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountPassword;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountVerification;
import com.paw.fund.app.modules.account_management.domain.account.usecase.IAccountUseCase;
import com.paw.fund.app.modules.account_management.domain.role.Role;
import com.paw.fund.app.modules.account_management.domain.role.usecase.IRoleUseCase;
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
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class AccountV1Controller implements IAccountV1API {
    @NonNull
    IAccountUseCase useCase;

    @NonNull
    IRoleUseCase roleUseCase;

    @NonNull
    IAccountModelMapper modelMapper;

    @Override
    public ValueResponse<AccountResponse> createAdmin(AccountRequest accountRequest) {
        List<Role> roles = List.of(roleUseCase.getAdminRole());
        Account account = modelMapper.toDto(accountRequest.ofSave(), roles);
        Account createdAccount = useCase.createAccount(AccountSave.of(account));

        return ValueResponse.success(modelMapper.toResponse(createdAccount), HttpStatus.CREATED);
    }

    @Override
    public ValueResponse<AccountResponse> createStaff(Long shelterId, AccountRequest accountRequest) {
        List<Role> roles = List.of(roleUseCase.getStaffRole());
        Account account = modelMapper.toDto(accountRequest.ofSave(), roles);
        Account createdAccount = useCase.createAccount(AccountSave.of(shelterId, account));

        return ValueResponse.success(modelMapper.toResponse(createdAccount), HttpStatus.CREATED);
    }

    @Override
    public ValueResponse<AccountResponse> selfChangeInfo(AccountRequest request) {
        Account updateAccount = modelMapper.toDto(request.ofUpdate());
        Account updatedAccount = useCase.selfChangeInfo(updateAccount);

        return ValueResponse.success(modelMapper.toResponse(updatedAccount), HttpStatus.OK);
    }

    @Override
    public ValueResponse<AccountResponse> selfChangePassword(AccountUpdatePasswordRequest accountUpdatePasswordRequest) {
        AccountPassword accountPassword = AccountPassword.of(accountUpdatePasswordRequest.password());
        Account account = useCase.selfChangePassword(accountPassword);

        return ValueResponse.success(modelMapper.toResponse(account), HttpStatus.OK);
    }

    @Override
    public ValueResponse<AccountResponse> verifyEmail(EmailVerificationCodeRequest emailVerifyCodeRequest) {
        Account account = useCase.verifyNewEmail(AccountVerification.of(emailVerifyCodeRequest.verificationCode()));

        return ValueResponse.success(modelMapper.toResponse(account), HttpStatus.OK);
    }

    @Override
    public ValueResponse<AccountResponse> getSelfAccountDetail() {
        Account account = useCase.getSelfDetail();

        return ValueResponse.success(modelMapper.toResponse(account), HttpStatus.OK);
    }
}

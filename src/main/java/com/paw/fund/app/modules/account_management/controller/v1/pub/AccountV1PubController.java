package com.paw.fund.app.modules.account_management.controller.v1.pub;

import com.paw.fund.app.modules.account_management.controller.models.AccountRequest;
import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.IAccountModelMapper;
import com.paw.fund.app.modules.account_management.controller.models.verification.code.AccountVerificationCodeRequest;
import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountFilter;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountSearchCriteria;
import com.paw.fund.app.modules.account_management.domain.usecase.AccountVerification;
import com.paw.fund.app.modules.account_management.service.usecase.IAccountUseCase;
import com.paw.fund.app.modules.role_management.domain.Role;
import com.paw.fund.app.modules.role_management.service.usecase.IRoleUseCase;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.response.Meta;
import com.paw.fund.utils.response.PageResponse;
import com.paw.fund.utils.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    IRoleUseCase roleUseCase;

    @NonNull
    IAccountModelMapper modelMapper;

    @Override
    public ValueResponse<AccountResponse> createAccount(AccountRequest accountRequest) {
        Account account = modelMapper.toDto(accountRequest);
        Account createdAccount = useCase.createAccount(account);

        return ValueResponse.success(modelMapper.toResponse(createdAccount), HttpStatus.CREATED, API_VERSION);
    }

    @Override
    public ValueResponse<AccountResponse> createDonorAndAdopterAccount(AccountRequest accountRequest) {
        List<Role> roles = List.of(roleUseCase.getDonorRole(), roleUseCase.getAdaptorRole());
        Account account = modelMapper.toDto(accountRequest, roles);
        Account createdAccount = useCase.createAccount(account);

        return ValueResponse.success(modelMapper.toResponse(createdAccount), HttpStatus.CREATED, API_VERSION);
    }

    @Override
    public PageResponse<AccountResponse> findAll(String search,
                                                 List<LocalDateTime> timeRange,
                                                 List<LocalDate> dateOfBirth,
                                                 List<String> statusCodes,
                                                 List<String> roleCodes,
                                                 List<String> genderCodes,
                                                 String sorter, Integer current, Integer pageSize) {
        AccountSearchCriteria searchCriteria = AccountSearchCriteria.of(
                search,
                timeRange,
                dateOfBirth,
                statusCodes,
                roleCodes,
                genderCodes);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize, sorter);
        AccountFilter filter = AccountFilter.builder()
                .searchCriteria(searchCriteria)
                .pageRequestCustom(pageRequestCustom)
                .build();
        Page<AccountResponse> responses = useCase.getAccountList(filter)
                .map(modelMapper::toResponse);

        return PageResponse.success(
                responses.getContent(),
                Meta.of(responses),
                HttpStatus.OK,
                API_VERSION);
    }

    @Override
    public ValueResponse<AccountResponse> verifyCreatedAccount(AccountVerificationCodeRequest verificationCode) {
        AccountVerification accountVerification = AccountVerification.of(verificationCode.email(),
                verificationCode.verificationCode());
        Account verifiedAccount = useCase.verifyCreatedAccount(accountVerification);

        return ValueResponse.success(modelMapper.toResponse(verifiedAccount), HttpStatus.OK, API_VERSION);
    }
}

package com.paw.fund.app.modules.category_management.controller.account.status;

import com.paw.fund.app.modules.category_management.controller.account.status.models.AccountStatusCategoryResponse;
import com.paw.fund.app.modules.category_management.controller.account.status.models.IAccountStatusCategoryModelMapper;
import com.paw.fund.app.modules.category_management.domain.usecase.AccountStatusSearch;
import com.paw.fund.app.modules.category_management.service.account.status.usecase.IAccountStatusCategoryUseCase;
import com.paw.fund.utils.response.ListResponse;
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
public class AccountStatusCategoryV1PubController implements IAccountStatusCategoryV1PubAPI{
    @NonNull
    IAccountStatusCategoryUseCase useCase;

    @NonNull
    IAccountStatusCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<AccountStatusCategoryResponse> getAccountStatusList(String search) {
        List<AccountStatusCategoryResponse> responses = useCase.getAccountStatusList(AccountStatusSearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.OK, API_VERSION);
    }
}

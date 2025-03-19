package com.paw.fund.app.modules.log_management.controller.account.v1;

import com.paw.fund.app.modules.log_management.controller.account.models.AccountActivityLogResponse;
import com.paw.fund.app.modules.log_management.controller.account.models.IAccountActivityLogModelMapper;
import com.paw.fund.app.modules.log_management.domain.account.usecase.AccountActivityLogFilter;
import com.paw.fund.app.modules.log_management.domain.account.usecase.AccountActivityLogSearchCriteria;
import com.paw.fund.app.modules.log_management.service.account.usecase.IAccountActivityLogUseCase;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.response.Meta;
import com.paw.fund.utils.response.PageResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountActivityLogV1Controller implements IAccountActivityLogV1API {
    @NonNull
    IAccountActivityLogUseCase useCase;

    @NonNull
    IAccountActivityLogModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public PageResponse<AccountActivityLogResponse> getSelfLog(String search,
                                                               List<LocalDateTime> timeRange,
                                                               List<String> actionCodes,
                                                               String sorter, Integer current, Integer pageSize) {
        AccountActivityLogSearchCriteria searchCriteria = AccountActivityLogSearchCriteria.of(
                search,
                actionCodes,
                timeRange);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize, sorter);
        AccountActivityLogFilter filter = AccountActivityLogFilter.of(searchCriteria, pageRequestCustom);
        Page<AccountActivityLogResponse> responses = useCase.getSelfLog(filter)
                .map(modelMapper::toResponse);

        return PageResponse.success(
                responses.getContent(),
                Meta.of(responses),
                HttpStatus.OK,
                API_VERSION);
    }
}

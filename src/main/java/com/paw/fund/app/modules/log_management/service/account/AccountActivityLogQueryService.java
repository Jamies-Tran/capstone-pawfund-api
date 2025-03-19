package com.paw.fund.app.modules.log_management.service.account;

import com.paw.fund.app.modules.log_management.domain.account.AccountActivityLog;
import com.paw.fund.app.modules.log_management.domain.account.IAccountActivityLogMapper;
import com.paw.fund.app.modules.log_management.domain.account.usecase.AccountActivityLogSearchCriteria;
import com.paw.fund.app.modules.log_management.repository.databse.account.AccountActivityLogEntity;
import com.paw.fund.app.modules.log_management.repository.databse.account.IAccountActivityLogRepository;
import com.paw.fund.utils.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountActivityLogQueryService {
    @NonNull
    IAccountActivityLogRepository repository;

    @NonNull
    IAccountActivityLogMapper mapper;

    public Page<AccountActivityLog> findAll(AccountActivityLogSearchCriteria searchCriteria,
                                            PageRequestCustom pageRequestCustom) {
        Page<AccountActivityLogEntity> foundAccountActivityLogs = repository
                .findAll(searchCriteria, pageRequestCustom.pageRequest());

        return foundAccountActivityLogs.map(mapper::toDto);
    }
}

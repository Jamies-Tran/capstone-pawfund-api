package com.paw.fund.app.modules.log_management.service.account;

import com.paw.fund.app.modules.log_management.domain.account.AccountActivityLog;
import com.paw.fund.app.modules.log_management.domain.account.IAccountActivityLogMapper;
import com.paw.fund.app.modules.log_management.repository.databse.account.AccountActivityLogEntity;
import com.paw.fund.app.modules.log_management.repository.databse.account.IAccountActivityLogRepository;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountActivityLogCommandService {
    @NonNull
    IAccountActivityLogRepository repository;

    @NonNull
    IAccountActivityLogMapper mapper;

    public AccountActivityLog save(AccountActivityLog accountActivityLog) {
        ValidationUtil.validateNotNullPointerException(accountActivityLog);
        AccountActivityLogEntity newAccountActivityLog = mapper.toEntity(accountActivityLog);
        AccountActivityLogEntity savedAccountActivityLog = repository.save(newAccountActivityLog);

        return mapper.toDto(savedAccountActivityLog);
    }

    public void deleteAllByAccountId(Long accountId) {
        ValidationUtil.validateArgumentNotNull(accountId);
        List<AccountActivityLogEntity> logs = repository.findAllByAccountId(accountId);

        repository.deleteAll(logs);
    }
}

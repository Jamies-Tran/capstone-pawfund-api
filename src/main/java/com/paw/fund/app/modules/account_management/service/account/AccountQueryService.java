package com.paw.fund.app.modules.account_management.service.account;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountIdAndThumbnail;
import com.paw.fund.app.modules.account_management.repository.database.account.AccountEntity;
import com.paw.fund.app.modules.account_management.repository.database.account.IAccountMapper;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountSearchCriteria;
import com.paw.fund.app.modules.account_management.repository.database.account.IAccountRepository;
import com.paw.fund.common.aspect.annotation.validate.args.ValidateArgs;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.enums.EVerificationType;
import com.paw.fund.utils.StringUtils;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountQueryService {
    @NonNull
    IAccountRepository repository;

    @NonNull
    IAccountMapper mapper;

    @ValidateArgs
    protected Account findById(Long accountId) {
        return repository.findById(accountId)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @ValidateArgs
    protected Account findByAccountEmail(String accountEmail) {
        ValidationUtil.validateArgumentNotNull(accountEmail);
        return repository.findByEmail(accountEmail)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @ValidateArgs
    protected Account findByVerificationCodeAndVerifyType(String verificationCode, EVerificationType verificationType) {
        return repository.findByVerificationCodeAndVerifyTypeCode(verificationCode, verificationType.getCode())
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @ValidateArgs
    protected Optional<Account> findByAccountEmailNullable(String accountEmail) {
        return repository.findByEmail(accountEmail)
                .map(mapper::toDto);
    }

    @ValidateArgs
    protected Boolean existsByAccountId(Long accountId) {
        return Optional.ofNullable(accountId)
                .map(repository::existsById)
                .orElse(false);
    }

    @ValidateArgs
    protected Page<Account> findAll(AccountSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        Page<AccountEntity> foundAccounts = repository.findAll(searchCriteria, pageRequestCustom.pageRequest());
        List<Long> accountIds = foundAccounts.map(AccountEntity::getAccountId).stream().toList();
        Map<Long, String> accountThumbnailMap = repository.findAccountIdAndThumbnailByAccountIdIn(accountIds)
                .stream()
                .collect(Collectors.toMap(AccountIdAndThumbnail::accountId, AccountIdAndThumbnail::thumbnail));
        return foundAccounts
                .map(account -> mapper.toDto(account).withThumbnail(accountThumbnailMap
                        .computeIfAbsent(account.getAccountId(), _ -> StringUtils.empty())));
    }
}

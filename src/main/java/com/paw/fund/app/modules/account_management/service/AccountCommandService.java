package com.paw.fund.app.modules.account_management.service;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.domain.IAccountMapper;
import com.paw.fund.app.modules.account_management.repository.database.AccountEntity;
import com.paw.fund.app.modules.account_management.repository.database.IAccountRepository;
import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.configuration.handler.exceptions.ResourceDuplicateException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.enums.EAccountStatus;
import com.paw.fund.enums.EDeleteStatus;
import com.paw.fund.utils.password.encoder.PawFundPasswordEncoder;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountCommandService {
    @NonNull
    IAccountRepository repository;

    @NonNull
    IAuditableUseCase auditableUseCase;

    @NonNull
    IAccountMapper mapper;

    @NonNull
    PawFundPasswordEncoder passwordEncoder;

    public Account save(Account account) {
        ValidationUtil.validateNotNullPointerException(account);
        validateNewAccount(account);

        AccountEntity newAccount = mapper.toEntity(account);
        newAccount.prepareSave(auditableUseCase.createAuditableForNew());
        AccountEntity savedAccount = repository.save(newAccount);
        return mapper.toDto(savedAccount);
    }

    private void validateNewAccount(Account account) {
        String msg = "";
        if(repository.existsByEmail(account.email())) {
            msg = "Email đã tồn tại";
        } else if(repository.existsByPhone(account.phone())) {
            msg = "Số điện thoại đã tồn tại";
        } else if(repository.existsByIdentification(account.identification())) {
            msg = "CCCD đã tồn tại";
        }

        if(StringUtils.hasText(msg)) {
            throw new ResourceDuplicateException(msg);
        }
    }

    public Account updateStatus(Long accountId, EAccountStatus status) {
        ValidationUtil.validateArgumentNotNull(accountId);
        ValidationUtil.validateArgumentNotNull(status);
        AccountEntity account = repository.findById(accountId)
                .orElseThrow(ResourceNotFoundException::new);
        account.setStatusCode(status.getCode());
        account.setStatusName(status.getName());
        account.prepareUpdate(auditableUseCase.createAuditableForUpdate());
        AccountEntity updatedAccount = repository.save(account);

        return mapper.toDto(updatedAccount);
    }

    public Account updateEmail(Long accountId, String email) {
        ValidationUtil.validateArgumentNotNull(accountId);
        ValidationUtil.validateArgumentNotNull(email);
        AccountEntity account = repository.findById(accountId)
                .orElseThrow(ResourceNotFoundException::new);
        account.setEmail(email);
        account.prepareUpdate(auditableUseCase.createAuditableForUpdate());
        AccountEntity updatedAccount = repository.save(account);

        return mapper.toDto(updatedAccount);
    }

    public Account update(Long accountId, Account account) {
        ValidationUtil.validateArgumentNotNull(accountId);
        ValidationUtil.validateNotNullPointerException(account);
        AccountEntity foundAccount = repository.findById(accountId)
                .orElseThrow(ResourceNotFoundException::new);
        validateUpdateAccount(foundAccount, account);
        mapper.update(foundAccount, account);
        foundAccount.prepareUpdate(auditableUseCase.createAuditableForUpdate());
        AccountEntity updatedAccount = repository.save(foundAccount);

        return mapper.toDto(updatedAccount);
    }

    private void validateUpdateAccount(AccountEntity foundAccount, Account account) {
        String msg = "";
        if(!Objects.equals(foundAccount.getEmail(), account.email())
                && repository.existsByEmail(account.email())) {
            msg = "Email đã tồn tại";
        } else if(!Objects.equals(foundAccount.getPhone(), account.phone())
                && repository.existsByPhone(account.phone())) {
            msg = "Số điện thoại đã tồn tại";
        } else if(!Objects.equals(foundAccount.getIdentification(), account.identification())
                && repository.existsByIdentification(account.identification())) {
            msg = "CCCD đã tồn tại";
        }

        if(StringUtils.hasText(msg)) {
            throw new ResourceDuplicateException(msg);
        }
    }

    public Account updatePassword(Long accountId, String password) {
        ValidationUtil.validateArgumentNotNull(accountId);
        ValidationUtil.validateArgumentNotNull(password);
        AccountEntity foundAccount = repository.findById(accountId)
                .orElseThrow(ResourceNotFoundException::new);
        foundAccount.setPassword(passwordEncoder.bCryptpasswordEncoder().encode(password));
        AccountEntity updatedAccount = repository.save(foundAccount);

        return mapper.toDto(updatedAccount);
    }

    public void delete(Long accountId) {
        ValidationUtil.validateArgumentNotNull(accountId);
        if(validateDelete(accountId)) {
            AccountEntity account = repository.findById(accountId)
                    .orElseThrow(ResourceNotFoundException::new);
            repository.delete(account);
        } else {
            throw new ResourceNotValidException("Không thể xóa tài khoản");
        }
    }

    private boolean validateDelete(Long accountId) {
        //TODO: Kiểm tra điều kiện xóa tài khoản
        return true;
    }
}

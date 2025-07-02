package com.paw.fund.app.modules.account_management.service.account;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.repository.database.account.IAccountMapper;
import com.paw.fund.app.modules.account_management.repository.database.account.AccountEntity;
import com.paw.fund.app.modules.account_management.repository.database.account.IAccountRepository;

import com.paw.fund.common.aspect.annotation.validate.args.ValidateArgs;
import com.paw.fund.configuration.handler.exceptions.ResourceDuplicateException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.enums.EAccountStatus;
import com.paw.fund.enums.EDeleteStatus;
import com.paw.fund.utils.password.encoder.PawFundPasswordEncoder;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountCommandService {
    @NonNull
    IAccountRepository repository;

    @NonNull
    IAccountMapper mapper;

    @NonNull
    PawFundPasswordEncoder passwordEncoder;

    @ValidateArgs
    protected Account save(Account account) {
        validateNewAccount(account);
        AccountEntity newAccount = mapper.toEntity(account);
        AccountEntity savedAccount = repository.save(newAccount);

        return mapper.toDto(savedAccount);
    }

    private void validateNewAccount(Account account) {
        boolean isDuplicatedEmail = repository.existsByEmail(account.email());
        boolean isDuplicatedPhone = repository.existsByPhone(account.phone());
        boolean isDuplicatedIdentification = repository.existsByIdentification(account.identification());

        if(isDuplicatedEmail) {
            throw new ResourceDuplicateException("Email đã tồn tại");
        }

        if(isDuplicatedPhone) {
            throw new ResourceDuplicateException("Số điện thoại đã tồn tại");
        }

        if(isDuplicatedIdentification) {
            throw new ResourceDuplicateException("CCCD đã tồn tại");
        }
    }

    @ValidateArgs
    protected Account updateStatus(Long accountId, EAccountStatus status) {
        return repository.findByAccountIdAndStatusCodeNotDeleted(accountId)
                .map(account -> {
                    account.setStatusCode(status.getCode());
                    account.setStatusName(status.getName());
                    return repository.save(account);
                })
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @ValidateArgs
    protected Account updateEmailByAccountIdAndVerificationCode(Long accountId, String verificationCode) {
        AccountEntity account = repository.findByAccountIdAndStatusCodeNotDeleted(accountId)
                .orElseThrow(ResourceNotFoundException::new);

        return repository.findNewEmailByAccountIdAndVerificationCode(accountId, verificationCode)
                .map(newEmail -> {
                    account.setEmail(newEmail);
                    return repository.save(account);
                })
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @ValidateArgs
    protected Account update(Long accountId, Account account) {
        return repository.findByAccountIdAndStatusCodeNotDeleted(accountId)
                .map(foundAccount -> {
                    validateUpdateAccount(foundAccount, account);
                    mapper.update(foundAccount, account);
                    return repository.save(foundAccount);
                })
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    private void validateUpdateAccount(AccountEntity foundAccount, Account account) {
        boolean isDuplicatedEmail = !Objects.equals(foundAccount.getEmail(), account.email())
                && repository.existsByEmail(account.email());

        boolean isDuplicatedPhone = !Objects.equals(foundAccount.getPhone(), account.phone())
                && repository.existsByPhone(account.phone());

        boolean isDuplicatedIdentification = !Objects.equals(foundAccount.getIdentification(), account.identification())
                && repository.existsByIdentification(account.identification());

        if(isDuplicatedEmail) {
            throw new ResourceDuplicateException("Email đã tồn tại");
        } else if(isDuplicatedPhone) {
            throw new ResourceDuplicateException("Số điện thoại đã tồn tại");
        } else if(isDuplicatedIdentification) {
            throw new ResourceDuplicateException("CCCD đã tồn tại");
        }
    }

    @ValidateArgs
    protected Account updatePassword(Long accountId, String password) {
        return repository.findByAccountIdAndStatusCodeNotDeleted(accountId)
                .map(account -> {
                    account.setPassword(passwordEncoder.bCryptpasswordEncoder().encode(password));
                    return repository.save(account);
                })
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @ValidateArgs
    protected void delete(Long accountId) {
        repository.findByAccountIdAndStatusCodeNotDeleted(accountId)
                .map(account -> {
                    account.setStatusCode(EDeleteStatus.DELETED.getCode());
                    account.setStatusName(EDeleteStatus.DELETED.getName());
                    return repository.save(account);
                })
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public void validateDelete(Long accountId) {
        //TODO: Kiểm tra điều kiện xóa tài khoản
    }
}

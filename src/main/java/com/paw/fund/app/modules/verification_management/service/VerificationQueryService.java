package com.paw.fund.app.modules.verification_management.service;

import com.paw.fund.app.modules.verification_management.domain.IVerificationMapper;
import com.paw.fund.app.modules.verification_management.domain.Verification;
import com.paw.fund.app.modules.verification_management.domain.usecase.data.transfer.AccountIdAndName;
import com.paw.fund.app.modules.verification_management.repository.database.IVerificationRepository;
import com.paw.fund.app.modules.verification_management.repository.database.VerificationEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.enums.EVerificationType;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerificationQueryService {
    @NonNull
    IVerificationRepository repository;

    @NonNull
    IVerificationMapper mapper;

    protected Verification findByCodeAndAccountIdAndVerificationCodeType(String code, Long accountId, EVerificationType verificationType) {
        ValidationUtil.validateArgumentNotNull(code);
        ValidationUtil.validateArgumentNotNull(accountId);
        ValidationUtil.validateArgumentNotNull(verificationType);
        VerificationEntity foundVerificationCode = repository
                .findByCodeAndAccountIdAndTypeCode(code, accountId, verificationType.getCode())
                .orElseThrow(ResourceNotFoundException::new);
        validateVerificationCode(foundVerificationCode);

        return mapper.toDto(foundVerificationCode);
    }

    private void validateVerificationCode(VerificationEntity verificationCode) {
        if(LocalDateTime.now().isAfter(verificationCode.getExpiredAt())) {
            throw new ResourceNotValidException("Mã xác nhận đã hết hạn");
        }
    }

    protected AccountIdAndName findAccountIdAndNameByEmail(String email) {
        return repository.findAccountIdByEmail(email)
                .orElseThrow(ResourceNotFoundException::new);
    }
}

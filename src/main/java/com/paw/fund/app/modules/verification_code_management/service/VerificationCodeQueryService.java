package com.paw.fund.app.modules.verification_code_management.service;

import com.paw.fund.app.modules.verification_code_management.domain.IVerificationCodeMapper;
import com.paw.fund.app.modules.verification_code_management.domain.VerificationCode;
import com.paw.fund.app.modules.verification_code_management.repository.database.IVerificationCodeRepository;
import com.paw.fund.app.modules.verification_code_management.repository.database.VerificationCodeEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.enums.EVerificationCodeType;
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
public class VerificationCodeQueryService {
    @NonNull
    IVerificationCodeRepository repository;

    @NonNull
    IVerificationCodeMapper mapper;

    public VerificationCode findByCodeAndAccountIdAndVerificationCodeType(String code, Long accountId, EVerificationCodeType verificationCodeType) {
        ValidationUtil.validateArgumentNotNull(code);
        ValidationUtil.validateArgumentNotNull(accountId);
        ValidationUtil.validateArgumentNotNull(verificationCodeType);
        VerificationCodeEntity foundVerificationCode = repository
                .findByCodeAndAccountIdAndTypeCode(code, accountId, verificationCodeType.getCode())
                .orElseThrow(ResourceNotFoundException::new);
        validateVerificationCode(foundVerificationCode);

        return mapper.toDto(foundVerificationCode);
    }

    private void validateVerificationCode(VerificationCodeEntity verificationCode) {
        if(LocalDateTime.now().isAfter(verificationCode.getExpiredAt())) {
            throw new ResourceNotValidException("Mã xác nhận đã hết hạn");
        }
    }
}

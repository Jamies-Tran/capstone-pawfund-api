package com.paw.fund.app.modules.verification_code_management.service;

import com.paw.fund.app.modules.verification_code_management.domain.IVerificationCodeMapper;
import com.paw.fund.app.modules.verification_code_management.domain.VerificationCode;
import com.paw.fund.app.modules.verification_code_management.repository.database.IVerificationCodeRepository;
import com.paw.fund.app.modules.verification_code_management.repository.database.VerificationCodeEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
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

    public VerificationCode findByCodeAndAccountId(String code, Long accountId) {
        ValidationUtil.validateArgumentNotNull(code);
        ValidationUtil.validateArgumentNotNull(accountId);
        VerificationCodeEntity foundVerificationCode = repository.findByCodeAndAccountId(code, accountId)
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

package com.paw.fund.app.modules.verification_code_management.service;

import com.paw.fund.app.modules.verification_code_management.domain.IVerificationCodeMapper;
import com.paw.fund.app.modules.verification_code_management.domain.VerificationCode;
import com.paw.fund.app.modules.verification_code_management.repository.database.IVerificationCodeRepository;
import com.paw.fund.app.modules.verification_code_management.repository.database.VerificationCodeEntity;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerificationCodeCommandService {
    @NonNull
    IVerificationCodeRepository repository;

    @NonNull
    IVerificationCodeMapper mapper;

    public VerificationCode save(VerificationCode verificationCode) {
        ValidationUtil.validateNotNullPointerException(verificationCode);
        VerificationCodeEntity newVerificationCode = mapper.toEntity(verificationCode);
        VerificationCodeEntity savedVerificationCode = repository.save(newVerificationCode);

        return mapper.toDto(savedVerificationCode);
    }

    public void delete(Long verificationCodeId) {
        repository.deleteById(verificationCodeId);
    }

}

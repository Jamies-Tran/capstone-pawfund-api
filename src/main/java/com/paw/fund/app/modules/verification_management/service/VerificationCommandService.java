package com.paw.fund.app.modules.verification_management.service;

import com.paw.fund.app.modules.verification_management.domain.IVerificationMapper;
import com.paw.fund.app.modules.verification_management.domain.Verification;
import com.paw.fund.app.modules.verification_management.repository.database.IVerificationRepository;
import com.paw.fund.app.modules.verification_management.repository.database.VerificationEntity;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerificationCommandService {
    @NonNull
    IVerificationRepository repository;

    @NonNull
    IVerificationMapper mapper;

    protected Verification save(Verification verification) {
        ValidationUtil.validateNotNullPointerException(verification);
        VerificationEntity newVerification = mapper.toEntity(verification);
        VerificationEntity savedVerification = repository.save(newVerification);

        return mapper.toDto(savedVerification);
    }

    protected void delete(Long verificationId) {
        repository.deleteById(verificationId);
    }

    protected void deleteByCode(String verificationCode) {
        repository.deleteByCode(verificationCode);
    }

}

package com.paw.fund.app.modules.media_management.service.verification;

import com.paw.fund.app.modules.media_management.domain.verification.IVerificationMediaMapper;
import com.paw.fund.app.modules.media_management.domain.verification.VerificationMedia;
import com.paw.fund.app.modules.media_management.repository.database.verification.IVerificationMediaRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerificationMediaQueryService {
    @NonNull
    IVerificationMediaRepository repository;

    @NonNull
    IVerificationMediaMapper mapper;

    public List<VerificationMedia> findAllByPetIntakeRegistrationId(Long petIntakeRegistrationId) {
        return repository.findAllByPetIntakeRegistrationId(petIntakeRegistrationId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}

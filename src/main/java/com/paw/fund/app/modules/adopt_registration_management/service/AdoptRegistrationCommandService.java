package com.paw.fund.app.modules.adopt_registration_management.service;

import com.paw.fund.app.modules.adopt_registration_management.domain.AdoptRegistration;
import com.paw.fund.app.modules.adopt_registration_management.domain.IAdoptRegistrationMapper;
import com.paw.fund.app.modules.adopt_registration_management.repository.database.AdoptRegistrationEntity;
import com.paw.fund.app.modules.adopt_registration_management.repository.database.IAdoptRegistrationRepository;
import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdoptRegistrationCommandService {
    @NonNull
    IAdoptRegistrationRepository repository;

    @NonNull
    IAdoptRegistrationMapper mapper;

    @NonNull
    IAuditableUseCase auditableUseCase;

    public AdoptRegistration save(AdoptRegistration adoptRegistration) {
        ValidationUtil.validateNotNullPointerException(adoptRegistration);
        AdoptRegistrationEntity newAdoptRegistration = mapper.toEntity(adoptRegistration);
        newAdoptRegistration.prepareSave(auditableUseCase.createAuditableForNew());
        AdoptRegistrationEntity saveAdoptRegistration = repository.save(newAdoptRegistration);

        return mapper.toDto(saveAdoptRegistration);
    }
}

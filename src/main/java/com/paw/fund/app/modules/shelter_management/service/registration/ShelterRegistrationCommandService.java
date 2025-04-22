package com.paw.fund.app.modules.shelter_management.service.registration;

import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.app.modules.shelter_management.domain.registration.IShelterRegistrationMapper;
import com.paw.fund.app.modules.shelter_management.domain.registration.ShelterRegistration;
import com.paw.fund.app.modules.shelter_management.repository.database.registration.IShelterRegistrationRepository;
import com.paw.fund.app.modules.shelter_management.repository.database.registration.ShelterRegistrationEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.enums.EShelterRegistrationStatus;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterRegistrationCommandService {
    @NonNull
    IShelterRegistrationRepository repository;

    @NonNull
    IShelterRegistrationMapper mapper;

    @NonNull
    IAuditableUseCase auditableUseCase;

    public ShelterRegistration save(ShelterRegistration shelterRegistration) {
        ValidationUtil.validateNotNullPointerException(shelterRegistration);
        ShelterRegistrationEntity newShelterReg = mapper.toEntity(shelterRegistration);
        newShelterReg.prepareSave(auditableUseCase.createAuditableForNew());
        ShelterRegistrationEntity saveShelterReg = repository.save(newShelterReg);

        return mapper.toDto(saveShelterReg);
    }

    public ShelterRegistration updateStatus(Long shelterRegistrationId,
                                            EShelterRegistrationStatus status,
                                            String rejectReason) {
        ValidationUtil.validateArgumentNotNull(shelterRegistrationId);
        ValidationUtil.validateArgumentNotNull(status);

        ShelterRegistrationEntity foundRegistration = repository.findById(shelterRegistrationId)
                .orElseThrow(ResourceNotFoundException::new);
        switch (status) {
            case RECEIVED -> {
                foundRegistration.setReceivedAt(LocalDateTime.now());
            }
            case APPROVED -> {
                foundRegistration.setApprovedAt(LocalDateTime.now());
            }
            case REJECTED -> {
                foundRegistration.setRejectedAt(LocalDateTime.now());
                foundRegistration.setReason(rejectReason);
            }
            default -> throw new ResourceNotValidException();
        }
        foundRegistration.setStatusCode(status.getCode());
        foundRegistration.setStatusName(status.getName());
        foundRegistration.prepareUpdate(auditableUseCase.createAuditableForUpdate());
        ShelterRegistrationEntity updatedRegistration = repository.save(foundRegistration);

        return mapper.toDto(updatedRegistration);
    }
}

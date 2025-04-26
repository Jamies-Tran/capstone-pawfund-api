package com.paw.fund.app.modules.shelter_management.service;

import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.app.modules.shelter_management.domain.IShelterMapper;
import com.paw.fund.app.modules.shelter_management.domain.Shelter;
import com.paw.fund.app.modules.shelter_management.repository.database.IShelterRepository;
import com.paw.fund.app.modules.shelter_management.repository.database.ShelterEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceDuplicateException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.enums.EShelterRegistrationStatus;
import com.paw.fund.enums.EShelterStatus;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterCommandService {
    @NonNull
    IShelterRepository repository;

    @NonNull
    IAuditableUseCase auditableUseCase;

    @NonNull
    IShelterMapper mapper;

    public Shelter save(Shelter shelter) {
        ValidationUtil.validateNotNullPointerException(shelter);
        validateSave(shelter);

        ShelterEntity newShelter = mapper.toEntity(shelter);
        ShelterEntity savedShelter = repository.save(newShelter);
        savedShelter.prepareSave(auditableUseCase.createAuditableForNew());

        return mapper.toDto(savedShelter);
    }

    public Shelter update(Long shelterId, Shelter shelter) {
        ValidationUtil.validateArgumentNotNull(shelterId);
        ValidationUtil.validateNotNullPointerException(shelter);

        return repository.findById(shelterId)
                .map(x -> {
                    mapper.update(x, shelter);
                    x.prepareUpdate(auditableUseCase.createAuditableForUpdate());

                    ShelterEntity savedShelter = repository.save(x);
                    return mapper.toDto(savedShelter);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Shelter updateStatusAndAccountRoleIdAndDescription(String description,
                                                              Long shelterId,
                                                              Long accountRoleId,
                                                              EShelterStatus status) {
        ValidationUtil.validateArgumentNotNull(shelterId);
        ValidationUtil.validateArgumentNotNull(status);

        return repository.findById(shelterId)
                .map(x -> {
                    x.setShelterCode(generateShelterCode(x));
                    x.setDescription(description);
                    x.setStatusCode(status.getCode());
                    x.setStatusName(status.getName());
                    x.setAccountRoleId(accountRoleId);
                    x.prepareUpdate(auditableUseCase.createAuditableForUpdate());
                    ShelterEntity savedShelter = repository.save(x);

                    return mapper.toDto(savedShelter);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    private String generateShelterCode(ShelterEntity shelter) {
        String createdAtAsText = shelter.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return "SH-%s-%s"
                .formatted(createdAtAsText, shelter.getShelterId());
    }

    private void validateSave(Shelter shelter) {
        if(repository.existsByEmail(shelter.email())) {
            throw new ResourceDuplicateException("Email đã tồn tại");
        }

        if(repository.existsByHotline(shelter.hotline())) {
            throw new ResourceDuplicateException("Hotline đã tồn tại");
        }
    }
}

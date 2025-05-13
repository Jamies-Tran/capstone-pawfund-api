package com.paw.fund.app.modules.pet_intake_registration_management.service;

import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.IPetIntakeRegistrationMapper;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistration;
import com.paw.fund.app.modules.pet_intake_registration_management.repository.database.IPetIntakeRegistrationRepository;
import com.paw.fund.app.modules.pet_intake_registration_management.repository.database.PetIntakeRegistrationEntity;
import com.paw.fund.configuration.handler.exceptions.AuthenticationException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.enums.EDeleteStatus;
import com.paw.fund.enums.EPetIntakeRegistrationStatus;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetIntakeRegistrationCommandService {
    @NonNull
    IPetIntakeRegistrationRepository repository;

    @NonNull
    IPetIntakeRegistrationMapper mapper;

    @NonNull
    IAuditableUseCase auditableUseCase;

    public PetIntakeRegistration save(PetIntakeRegistration petIntakeRegistration) {
        ValidationUtil.validateNotNullPointerException(petIntakeRegistration);
        PetIntakeRegistrationEntity newPetIntakeRegistration = mapper.toEntity(petIntakeRegistration);
        PetIntakeRegistrationEntity savedPetIntakeRegistration = repository.save(newPetIntakeRegistration);
        savedPetIntakeRegistration.prepareSave(auditableUseCase.createAuditableForNew());

        return mapper.toDto(savedPetIntakeRegistration);
    }

    public PetIntakeRegistration update(Long petIntakeRegistrationId, PetIntakeRegistration petIntakeRegistration) {
        ValidationUtil.validateArgumentNotNull(petIntakeRegistrationId);
        ValidationUtil.validateNotNullPointerException(petIntakeRegistration);

        return repository.findByStatusCodeNotDeletedAndPetIntakeRegistrationId(petIntakeRegistrationId)
                .map(x -> {
                    validateUpdate(x);
                    mapper.update(x, petIntakeRegistration);
                    x.prepareUpdate(auditableUseCase.createAuditableForUpdate());
                    PetIntakeRegistrationEntity updatedPetIntakeRegistration = repository.save(x);

                    return mapper.toDto(updatedPetIntakeRegistration);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    private void validateUpdate(PetIntakeRegistrationEntity existedPetIntakeRegistration) {
        if(!Objects.equals(existedPetIntakeRegistration.getStatusCode(), EPetIntakeRegistrationStatus.NEW.getCode())) {
            throw new ResourceNotValidException();
        }
    }

    public void deleteWithVerificationByPhone(Long petIntakeRegistrationId, String phone) {
        repository.findByStatusCodeNotDeletedAndPetIntakeRegistrationId(petIntakeRegistrationId)
                .ifPresentOrElse(
                        x -> {
                            validateDelete(x, phone);
                            x.setStatusCode(EDeleteStatus.DELETED.getCode());
                            x.setStatusName(EDeleteStatus.DELETED.getName());
                            x.prepareUpdate(auditableUseCase.createAuditableForUpdate());

                            repository.save(x);
                        },
                        () -> {
                            throw new ResourceNotFoundException();
                        }
                );
    }

    private void validateDelete(PetIntakeRegistrationEntity existedPetIntakeRegistration, String phone) {
        if(!Objects.equals(existedPetIntakeRegistration.getStatusCode(), EPetIntakeRegistrationStatus.NEW.getCode())) {
            throw new ResourceNotValidException();
        } else if(!Objects.equals(existedPetIntakeRegistration.getInformerPhone(), phone)) {
            throw new AuthenticationException("Hiện tại bạn không thể sử dụng tính năng này");
        }
    }
}

package com.paw.fund.app.modules.log_management.service.pet;

import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.app.modules.log_management.domain.pet.IPetActivityLogMapper;
import com.paw.fund.app.modules.log_management.domain.pet.PetActivityLog;
import com.paw.fund.app.modules.log_management.repository.databse.pet.IPetActivityLogRepository;
import com.paw.fund.app.modules.log_management.repository.databse.pet.PetActivityLogEntity;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetActivityLogCommandService {
    @NonNull
    IPetActivityLogRepository repository;

    @NonNull
    IPetActivityLogMapper mapper;

    @NonNull
    IAuditableUseCase auditableUseCase;

    public void save(PetActivityLog petActivityLog) {
        PetActivityLogEntity newPetActivityLog = mapper.toEntity(petActivityLog);
        newPetActivityLog.prepareSave(auditableUseCase.createAuditableForNew());

        repository.save(newPetActivityLog);
    }
}

package com.paw.fund.app.modules.pet_management.service.health.record;

import com.paw.fund.app.modules.log_management.annotation.CreatePetActivityLogHelper;
import com.paw.fund.app.modules.pet_management.aspect.GetPetHealthRecordDetailHelper;
import com.paw.fund.app.modules.pet_management.domain.health.record.PetHealthRecord;
import com.paw.fund.app.modules.pet_management.domain.health.record.usecase.PetHealthRecordFilter;
import com.paw.fund.app.modules.pet_management.domain.health.record.usecase.PetHealthRecordId;
import com.paw.fund.app.modules.pet_management.domain.health.record.usecase.PetHealthRecordUpdate;
import com.paw.fund.app.modules.pet_management.service.health.record.usecase.IPetHealthRecordUseCase;
import com.paw.fund.enums.EPetAction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetHealthRecordUseCaseService implements IPetHealthRecordUseCase {
    @NonNull
    PetHealthRecordCommandService commandService;

    @NonNull
    PetHealthRecordQueryService queryService;

    @Override
    @Transactional
    @CreatePetActivityLogHelper(action = EPetAction.UPDATED_HEALTH_STATUS)
    public PetHealthRecord createPetHealthRecord(PetHealthRecord petHealthRecord) {
        return commandService.save(petHealthRecord);
    }

    @Override
    @GetPetHealthRecordDetailHelper
    public PetHealthRecord getPetHealthRecordDetail(PetHealthRecordId petHealthRecordId) {

        return queryService.findById(petHealthRecordId.value());
    }

    @Override
    public Page<PetHealthRecord> getPetHealthRecordList(PetHealthRecordFilter filter) {
        return queryService.findAll(filter.searchCriteria(), filter.pageRequestCustom());
    }

    @Override
    @CreatePetActivityLogHelper(action = EPetAction.UPDATED_HEALTH_STATUS)
    public PetHealthRecord updatePetHealthRecord(PetHealthRecordUpdate petHealthRecordUpdate) {

        return commandService.update(petHealthRecordUpdate.petHealthRecordId(), petHealthRecordUpdate.petHealthRecord());
    }
}

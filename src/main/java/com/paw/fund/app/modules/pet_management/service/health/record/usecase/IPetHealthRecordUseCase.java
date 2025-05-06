package com.paw.fund.app.modules.pet_management.service.health.record.usecase;

import com.paw.fund.app.modules.pet_management.domain.health.record.PetHealthRecord;
import com.paw.fund.app.modules.pet_management.domain.health.record.usecase.PetHealthRecordFilter;
import com.paw.fund.app.modules.pet_management.domain.health.record.usecase.PetHealthRecordId;
import com.paw.fund.app.modules.pet_management.domain.health.record.usecase.PetHealthRecordUpdate;
import org.springframework.data.domain.Page;

public interface IPetHealthRecordUseCase {
    PetHealthRecord createPetHealthRecord(PetHealthRecord petHealthRecord);

    PetHealthRecord getPetHealthRecordDetail(PetHealthRecordId petHealthRecordId);

    Page<PetHealthRecord> getPetHealthRecordList(PetHealthRecordFilter filter);

    PetHealthRecord updatePetHealthRecord(PetHealthRecordUpdate petHealthRecordUpdate);
}

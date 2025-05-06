package com.paw.fund.app.modules.pet_management.domain.health.record.usecase;

import com.paw.fund.app.modules.pet_management.domain.health.record.PetHealthRecord;
import lombok.Builder;

@Builder
public record PetHealthRecordUpdate(
        Long petHealthRecordId,
        PetHealthRecord petHealthRecord
) {
    public static PetHealthRecordUpdate of(Long petHealthRecordId, PetHealthRecord petHealthRecord) {
        return PetHealthRecordUpdate.builder()
                .petHealthRecordId(petHealthRecordId)
                .petHealthRecord(petHealthRecord)
                .build();
    }
}

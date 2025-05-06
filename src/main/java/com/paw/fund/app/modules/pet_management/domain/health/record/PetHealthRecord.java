package com.paw.fund.app.modules.pet_management.domain.health.record;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.pet_management.domain.pet.Pet;
import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PetHealthRecord(
        Long petHealthRecordId,
        Long petId,
        @With Pet pet,
        @With Account staffLoggedRecord,
        LocalDateTime checkupDate,
        BigDecimal weight,
        String conditionDescription,
        String treatment,
        String diagnosis,
        String healthStatusCode,
        String healthStatusName,
        Long createdById,
        @With PetHealthRecordAction action) {
}

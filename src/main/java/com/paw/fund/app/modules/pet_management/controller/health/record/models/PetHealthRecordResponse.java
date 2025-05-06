package com.paw.fund.app.modules.pet_management.controller.health.record.models;

import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.pet_management.controller.pet.models.PetResponse;
import com.paw.fund.app.modules.pet_management.domain.health.record.PetHealthRecordAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PetHealthRecordResponse(
        Long petHealthRecordId,
        Long petId,
        PetResponse pet,
        AccountResponse staffLoggedRecord,
        LocalDateTime checkupDate,
        BigDecimal weight,
        String conditionDescription,
        String treatment,
        String diagnosis,
        String healthStatusCode,
        String healthStatusName,
        PetHealthRecordAction action
) {
}

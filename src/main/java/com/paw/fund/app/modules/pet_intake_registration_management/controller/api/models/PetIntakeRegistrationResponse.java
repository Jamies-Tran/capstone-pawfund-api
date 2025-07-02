package com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models;

import com.paw.fund.app.modules.media_management.domain.verification.VerificationMedia;
import com.paw.fund.app.modules.pet_intake_registration_management.domain.PetIntakeRegistrationAction;
import com.paw.fund.app.modules.pet_management.domain.type.PetType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PetIntakeRegistrationResponse(
        Long petIntakeRegistrationId,
        Long accountId,
        Long petTypeId,
        PetType petType,
        String informerPhone,
        String petDescription,
        String reasonTypeCode,
        String reasonTypeName,
        String address,
        BigDecimal latitude,
        BigDecimal longitude,
        List<VerificationMedia> medias,
        String cancelReason,
        String statusCode,
        String statusName,
        LocalDateTime createdAt,
        Long createdById,
        String createdByName,
        PetIntakeRegistrationActionResponse action
) {
}

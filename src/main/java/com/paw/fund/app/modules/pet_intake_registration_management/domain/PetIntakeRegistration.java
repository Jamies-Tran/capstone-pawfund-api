package com.paw.fund.app.modules.pet_intake_registration_management.domain;

import com.paw.fund.app.modules.media_management.domain.verification.VerificationMedia;
import com.paw.fund.app.modules.pet_management.domain.type.PetType;
import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PetIntakeRegistration(
        Long petIntakeRegistrationId,
        @With Long accountId,
        Long petTypeId,
        @With PetType petType,
        String placeId,
        String informerPhone,
        String petDescription,
        String reasonTypeCode,
        String reasonTypeName,
        @With String address,
        @With BigDecimal latitude,
        @With BigDecimal longitude,
        @With List<VerificationMedia> medias,
        @With String canceledReason,
        String statusCode,
        String statusName,
        LocalDateTime createdAt,
        Long createdById,
        String createdByName,
        @With PetIntakeRegistrationAction action
) {
}

package com.paw.fund.app.modules.pet_management.controller.health.record.models;

import com.paw.fund.app.modules.pet_management.domain.health.record.PetHealthRecord;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IPetHealthRecordModelMapper {
    PetHealthRecord toDto(PetHealthRecordRequest request);

    PetHealthRecord toDto(PetHealthRecordUpdateRequest request);

    PetHealthRecordResponse toResponse(PetHealthRecord dto);
}

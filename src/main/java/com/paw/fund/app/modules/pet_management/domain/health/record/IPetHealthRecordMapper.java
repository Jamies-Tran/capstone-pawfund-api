package com.paw.fund.app.modules.pet_management.domain.health.record;

import com.paw.fund.app.modules.pet_management.repository.database.health.record.PetHealthRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IPetHealthRecordMapper {
    PetHealthRecord toDto(PetHealthRecordEntity entity);

    PetHealthRecordEntity toEntity(PetHealthRecord dto);

    void update(@MappingTarget PetHealthRecordEntity entity, PetHealthRecord dto);
}

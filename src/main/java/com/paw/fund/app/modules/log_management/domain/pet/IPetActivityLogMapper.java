package com.paw.fund.app.modules.log_management.domain.pet;

import com.paw.fund.app.modules.log_management.repository.databse.pet.PetActivityLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IPetActivityLogMapper {
    PetActivityLog toDto(PetActivityLogEntity entity);

    PetActivityLogEntity toEntity(PetActivityLog dto);
}

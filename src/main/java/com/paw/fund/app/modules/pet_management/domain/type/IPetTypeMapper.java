package com.paw.fund.app.modules.pet_management.domain.type;

import com.paw.fund.app.modules.pet_management.repository.database.type.PetTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface IPetTypeMapper {
    PetType toDto(PetTypeEntity entity);

    PetTypeEntity toEntity(PetType dto);

    void update(@MappingTarget PetTypeEntity entity, PetType dto);
}

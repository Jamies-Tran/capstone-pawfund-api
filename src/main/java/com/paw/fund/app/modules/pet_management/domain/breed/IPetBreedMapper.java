package com.paw.fund.app.modules.pet_management.domain.breed;

import com.paw.fund.app.modules.pet_management.repository.database.breed.PetBreedEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IPetBreedMapper {
    PetBreed toDto(PetBreedEntity entity);

    PetBreedEntity toEntity(PetBreed dto);

    void update(@MappingTarget PetBreedEntity entity, PetBreed dto);
}

package com.paw.fund.app.modules.pet_management.domain.pet.hobby;

import com.paw.fund.app.modules.pet_management.repository.database.pet.hobby.PetHobbyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IPetHobbyMapper {
    PetHobby toDto(PetHobbyEntity entity);

    PetHobbyEntity toEntity(PetHobby dto);

    void update(@MappingTarget PetHobbyEntity entity, PetHobby dto);
}

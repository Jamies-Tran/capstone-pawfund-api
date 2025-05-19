package com.paw.fund.app.modules.pet_management.domain.pet;

import com.paw.fund.app.modules.pet_management.repository.database.pet.PetEntity;
import com.paw.fund.app.modules.pet_management.repository.database.pet.dao.PetSummarizeInfoDAO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IPetMapper {
    Pet toDto(PetEntity entity);

    PetSummarizeInfo toDto(PetSummarizeInfoDAO dao);

    PetEntity toEntity(Pet dto);

    void update(@MappingTarget PetEntity entity, Pet dto);
}

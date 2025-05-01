package com.paw.fund.app.modules.pet_management.domain.hobby;

import com.paw.fund.app.modules.pet_management.repository.database.hobby.HobbyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IHobbyMapper {
    Hobby toDto(HobbyEntity entity);

    HobbyEntity toEntity(Hobby dto);

    void update(@MappingTarget HobbyEntity entity, Hobby dto);
}

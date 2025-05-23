package com.paw.fund.app.modules.shelter_assignment_management.domain;

import com.paw.fund.app.modules.shelter_assignment_management.repository.database.ShelterAssignmentEntity;
import com.paw.fund.app.modules.shelter_assignment_management.repository.database.dao.ShelterAssignmentActionDAO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IShelterAssignmentMapper {
    ShelterAssignment toDto(ShelterAssignmentEntity entity);

    ShelterAssignmentEntity toEntity(ShelterAssignment dto);

    void update(@MappingTarget ShelterAssignmentEntity entity, ShelterAssignment dto);

    ShelterAssignmentAction toDto(ShelterAssignmentActionDAO dao);
}

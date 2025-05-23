package com.paw.fund.app.modules.shelter_assignment_management.controller.api.models;

import com.paw.fund.app.modules.shelter_assignment_management.domain.ShelterAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IShelterAssignmentModelMapper {
    ShelterAssignmentResponse toResponse(ShelterAssignment dto);

    ShelterAssignment toDto(ShelterAssignmentRequest request);
}

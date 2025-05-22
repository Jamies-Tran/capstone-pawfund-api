package com.paw.fund.app.modules.shelter_assignment_management.service.usecase;

import com.paw.fund.app.modules.shelter_assignment_management.domain.ShelterAssignment;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentCreateList;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentFilter;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentId;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentReject;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentUpdate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IShelterAssignmentUseCase {
    List<ShelterAssignment> createShelterAssignmentList(ShelterAssignmentCreateList shelterAssignmentCreateList);

    ShelterAssignment getShelterAssignmentDetail(ShelterAssignmentId shelterAssignmentId);

    Page<ShelterAssignment> getShelterAssignmentList(ShelterAssignmentFilter shelterAssignmentFilter);

    ShelterAssignment updateShelterAssignment(ShelterAssignmentUpdate shelterAssignmentUpdate);

    ShelterAssignment receiveShelterAssignment(ShelterAssignmentId shelterAssignmentId);

    ShelterAssignment rejectShelterAssignment(ShelterAssignmentReject shelterAssignmentReject);

    ShelterAssignment completeShelterAssignment(ShelterAssignmentId shelterAssignmentId);
}

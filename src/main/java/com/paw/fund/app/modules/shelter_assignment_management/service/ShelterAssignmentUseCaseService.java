package com.paw.fund.app.modules.shelter_assignment_management.service;

import com.paw.fund.app.modules.shelter_assignment_management.aspect.ChangeShelterAssignmentStatusHelper;
import com.paw.fund.app.modules.shelter_assignment_management.aspect.GetShelterAssignmentDetailHelper;
import com.paw.fund.app.modules.shelter_assignment_management.aspect.NotifyHelper;
import com.paw.fund.app.modules.shelter_assignment_management.domain.ShelterAssignment;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentCreateList;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentFilter;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentId;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentReject;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentUpdate;
import com.paw.fund.app.modules.shelter_assignment_management.service.usecase.IShelterAssignmentUseCase;
import com.paw.fund.enums.EShelterAssignmentStatus;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterAssignmentUseCaseService implements IShelterAssignmentUseCase {
    @NonNull
    ShelterAssignmentCommandService commandService;

    @NonNull
    ShelterAssignmentQueryService queryService;

    @Override
    @Transactional
    @NotifyHelper(variableAppDestination = "/topic/shelter-assignment")
    public List<ShelterAssignment> createShelterAssignmentList(ShelterAssignmentCreateList shelterAssignmentCreateList) {
        List<ShelterAssignment> shelterAssignments = shelterAssignmentCreateList.shelterIds().stream()
                .map(x -> {
                    Long petIntakeRegistrationId = shelterAssignmentCreateList.petIntakeRegistrationId();

                    return ShelterAssignment.builder()
                            .shelterId(x)
                            .petIntakeRegistrationId(petIntakeRegistrationId)
                            .build();
                })
                .toList();

        return commandService.saveAll(shelterAssignments);
    }

    @Override
    @GetShelterAssignmentDetailHelper
    public ShelterAssignment getShelterAssignmentDetail(ShelterAssignmentId shelterAssignmentId) {
        return queryService.findById(shelterAssignmentId.value());
    }

    @Override
    public Page<ShelterAssignment> getShelterAssignmentList(ShelterAssignmentFilter shelterAssignmentFilter) {
        return queryService.findAll(shelterAssignmentFilter.searchCriteria(), shelterAssignmentFilter.pageRequestCustom());
    }

    @Override
    @Transactional
    @NotifyHelper(variableAppDestination = "/topic/shelter-assignment")
    public ShelterAssignment updateShelterAssignment(ShelterAssignmentUpdate shelterAssignmentUpdate) {
        return commandService.update(shelterAssignmentUpdate.shelterAssignmentId(), shelterAssignmentUpdate.shelterAssignment());
    }

    @Override
    @Transactional
    @NotifyHelper(variableAppDestination = "/topic/shelter-assignment")
    @ChangeShelterAssignmentStatusHelper(status = EShelterAssignmentStatus.RECEIVED)
    public ShelterAssignment receiveShelterAssignment(ShelterAssignmentId shelterAssignmentId) {
        return commandService.updateStatus(
                shelterAssignmentId.value(),
                EShelterAssignmentStatus.RECEIVED,
                null);
    }

    @Override
    @Transactional
    @NotifyHelper(variableAppDestination = "/topic/shelter-assignment")
    @ChangeShelterAssignmentStatusHelper(status = EShelterAssignmentStatus.REJECTED)
    public ShelterAssignment rejectShelterAssignment(ShelterAssignmentReject shelterAssignmentReject) {
        return commandService.updateStatus(
                shelterAssignmentReject.shelterAssignmentId(),
                EShelterAssignmentStatus.REJECTED,
                shelterAssignmentReject.cancelReason());
    }

    @Override
    @Transactional
    @NotifyHelper(variableAppDestination = "/topic/shelter-assignment")
    @ChangeShelterAssignmentStatusHelper(status = EShelterAssignmentStatus.COMPLETED)
    public ShelterAssignment completeShelterAssignment(ShelterAssignmentId shelterAssignmentId) {
        return commandService.updateStatus(
                shelterAssignmentId.value(),
                EShelterAssignmentStatus.COMPLETED,
                null
        );
    }
}

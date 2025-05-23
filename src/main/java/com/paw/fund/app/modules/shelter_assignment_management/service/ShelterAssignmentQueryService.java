package com.paw.fund.app.modules.shelter_assignment_management.service;

import com.paw.fund.app.modules.shelter_assignment_management.domain.IShelterAssignmentMapper;
import com.paw.fund.app.modules.shelter_assignment_management.domain.ShelterAssignment;
import com.paw.fund.app.modules.shelter_assignment_management.domain.ShelterAssignmentAction;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentSearchCriteria;
import com.paw.fund.app.modules.shelter_assignment_management.repository.database.IShelterAssignmentRepository;
import com.paw.fund.app.modules.shelter_assignment_management.repository.database.ShelterAssignmentEntity;
import com.paw.fund.app.modules.shelter_assignment_management.repository.database.dao.ShelterAssignmentActionDAO;
import com.paw.fund.configuration.handler.exceptions.ResourceDuplicateException;
import com.paw.fund.utils.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterAssignmentQueryService {
    @NonNull
    IShelterAssignmentRepository repository;

    @NonNull
    IShelterAssignmentMapper mapper;

    public ShelterAssignment findById(Long shelterAssignmentId) {
        return repository.findById(shelterAssignmentId)
                .map(mapper::toDto)
                .orElseThrow(ResourceDuplicateException::new);
    }

    public Page<ShelterAssignment> findAll(ShelterAssignmentSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        Page<ShelterAssignmentEntity> shelterAssignments = repository
                .findAll(searchCriteria, pageRequestCustom.pageRequest());
        List<Long> shelterAssignmentIds = shelterAssignments.stream()
                .map(ShelterAssignmentEntity::getShelterAssignmentId)
                .toList();
        Map<Long, ShelterAssignmentAction> actionMap = repository.findShelterAssignmentActionByIdIn(shelterAssignmentIds)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toMap(ShelterAssignmentAction::shelterAssignmentId, action -> action));
        return shelterAssignments
                .map(x -> mapper
                        .toDto(x)
                        .withAction(actionMap.computeIfAbsent(x.getShelterAssignmentId(), _ -> ShelterAssignmentAction.ofDefault(x.getShelterAssignmentId()))));
    }
}

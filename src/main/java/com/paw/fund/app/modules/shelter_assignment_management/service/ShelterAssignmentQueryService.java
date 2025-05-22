package com.paw.fund.app.modules.shelter_assignment_management.service;

import com.paw.fund.app.modules.shelter_assignment_management.domain.IShelterAssignmentMapper;
import com.paw.fund.app.modules.shelter_assignment_management.domain.ShelterAssignment;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentSearchCriteria;
import com.paw.fund.app.modules.shelter_assignment_management.repository.database.IShelterAssignmentRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceDuplicateException;
import com.paw.fund.utils.request.PageRequestCustom;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
        return repository.findAll(searchCriteria, pageRequestCustom.pageRequest())
                .map(mapper::toDto);
    }
}

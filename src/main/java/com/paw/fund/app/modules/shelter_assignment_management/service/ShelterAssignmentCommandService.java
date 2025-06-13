package com.paw.fund.app.modules.shelter_assignment_management.service;

import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.app.modules.shelter_assignment_management.domain.IShelterAssignmentMapper;
import com.paw.fund.app.modules.shelter_assignment_management.domain.ShelterAssignment;
import com.paw.fund.app.modules.shelter_assignment_management.repository.database.IShelterAssignmentRepository;
import com.paw.fund.app.modules.shelter_assignment_management.repository.database.ShelterAssignmentEntity;
import com.paw.fund.app.modules.shelter_assignment_management.repository.database.dao.ShelterAssignmentActionDAO;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.enums.EShelterAssignmentStatus;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterAssignmentCommandService {
    @NonNull
    IShelterAssignmentRepository repository;

    @NonNull
    IShelterAssignmentMapper mapper;

    @NonNull
    IAuditableUseCase auditableUseCase;

    public List<ShelterAssignment> saveAll(List<ShelterAssignment> shelterAssignments) {
        List<ShelterAssignmentEntity> newShelterAssignment = shelterAssignments.stream()
                .map(mapper::toEntity)
                .peek(x -> x.prepareSave(auditableUseCase.createAuditableForNew()))
                .toList();

        return repository.saveAll(newShelterAssignment)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public ShelterAssignment update(Long shelterAssignmentId, ShelterAssignment shelterAssignment) {
        return repository.findById(shelterAssignmentId)
                .map(x -> {
                    validateUpdate(x);
                    mapper.update(x, shelterAssignment);
                    x.prepareUpdate(auditableUseCase.createAuditableForUpdate());
                    ShelterAssignmentEntity savedShelterAssignment = repository.save(x);

                    return mapper.toDto(savedShelterAssignment);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    private void validateUpdate(ShelterAssignmentEntity entity) {
        if(!Objects.equals(entity.getStatusCode(), EShelterAssignmentStatus.NEW.getCode())) {
            throw new ResourceNotValidException("Không thể cập nhật phân công lúc này");
        }
    }

    public ShelterAssignment updateStatus(Long shelterAssignmentId, EShelterAssignmentStatus status, String cancelReason) {
        return repository.findById(shelterAssignmentId)
                .map(x -> {
                    validUpdateStatus(x.getShelterAssignmentId(), status);
                    x.setStatusCode(status.getCode());
                    x.setStatusName(status.getName());
                    x.setCancelReason(cancelReason);
                    x.prepareUpdate(auditableUseCase.createAuditableForUpdate());
                    ShelterAssignmentEntity savedShelterAssignment = repository.save(x);

                    return mapper.toDto(savedShelterAssignment);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    private void validUpdateStatus(Long shelterAssignmentId, EShelterAssignmentStatus status) {
        ShelterAssignmentActionDAO actions = repository.findShelterAssignmentActionById(shelterAssignmentId)
                .orElseThrow(ResourceNotFoundException::new);
        switch (status) {
            case RECEIVED -> {
                if(!actions.getAllowReceive()) {
                    throw new ResourceNotValidException();
                }
            }

            case CANCELED -> {
                if(!actions.getAllowCancel()) {
                    throw new ResourceNotValidException();
                }
            }

            case COMPLETED -> {
                if(!actions.getAllowComplete()) {
                    throw new ResourceNotValidException();
                }
            }
        }
    }

    public List<ShelterAssignment> updateStatusByPetIntakeRegistrationIdExceptById(Long petIntakeRegistrationId,
                                                                                   Long shelterAssignmentId,
                                                                                   EShelterAssignmentStatus status,
                                                                                   String cancelReason) {
        List<ShelterAssignmentEntity> shelterAssignments = repository
                .findAllByPetIntakeRegistrationIdAndShelterAssignmentIdNot(petIntakeRegistrationId, shelterAssignmentId);
        List<Long> shelterAssignmentIds = shelterAssignments.stream()
                .map(ShelterAssignmentEntity::getShelterAssignmentId)
                .toList();
        Map<Long, ShelterAssignmentActionDAO> actionMap = repository.findShelterAssignmentActionByIdIn(shelterAssignmentIds)
                .stream()
                .collect(Collectors.toMap(ShelterAssignmentActionDAO::getShelterAssignmentId, action -> action));

        return shelterAssignments.stream()
                .map(x -> {
                    ShelterAssignmentActionDAO action = actionMap.get(x.getShelterAssignmentId());
                    switch (status) {
                        case RECEIVED -> {
                            if(action.getAllowReceive()) {
                                x.setStatusCode(status.getCode());
                                x.setStatusName(status.getName());
                            }
                        }
                        case CANCELED -> {
                            x.setStatusCode(status.getCode());
                            x.setStatusName(status.getName());
                            x.setCancelReason(cancelReason);
                        }

                        case COMPLETED -> {
                            if(action.getAllowComplete()) {
                                x.setStatusCode(status.getCode());
                                x.setStatusName(status.getName());
                            }
                        }
                    }
                    x.prepareUpdate(auditableUseCase.createAuditableForUpdate());
                    ShelterAssignmentEntity savedShelterAssignment = repository.save(x);

                    return mapper.toDto(savedShelterAssignment);
                })
                .toList();
    }
}

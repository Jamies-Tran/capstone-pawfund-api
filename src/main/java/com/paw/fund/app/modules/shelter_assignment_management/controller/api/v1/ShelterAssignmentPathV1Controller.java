package com.paw.fund.app.modules.shelter_assignment_management.controller.api.v1;

import com.paw.fund.app.modules.shelter_assignment_management.ShelterAssignmentModuleConstant;
import com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.IShelterAssignmentModelMapper;
import com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.ShelterAssignmentRequest;
import com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.ShelterAssignmentResponse;
import com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.reason.ShelterAssignmentCancelReason;
import com.paw.fund.app.modules.shelter_assignment_management.domain.ShelterAssignment;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentId;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentCancel;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentUpdate;
import com.paw.fund.app.modules.shelter_assignment_management.service.usecase.IShelterAssignmentUseCase;
import com.paw.fund.utils.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterAssignmentPathV1Controller implements IShelterAssignmentPathV1API {
    @NonNull
    IShelterAssignmentUseCase useCase;

    @NonNull
    IShelterAssignmentModelMapper modelMapper;

    @Override
    public ValueResponse<ShelterAssignmentResponse> getShelterAssignmentDetail(Long shelterAssignmentId) {
        ShelterAssignment shelterAssignment = useCase
                .getShelterAssignmentDetail(ShelterAssignmentId.of(shelterAssignmentId));

        return ValueResponse.success(modelMapper.toResponse(shelterAssignment), HttpStatus.OK);
    }

    @Override
    public ValueResponse<ShelterAssignmentResponse> updateShelterAssignment(Long shelterAssignmentId, ShelterAssignmentRequest request) {
        ShelterAssignment shelterAssignment = useCase
                .updateShelterAssignment(ShelterAssignmentUpdate.of(shelterAssignmentId, modelMapper.toDto(request)));

        return ValueResponse.success(modelMapper.toResponse(shelterAssignment), HttpStatus.OK);
    }

    @Override
    public ValueResponse<ShelterAssignmentResponse> receiveShelterAssignment(Long shelterAssignmentId) {
        ShelterAssignment shelterAssignment = useCase
                .receiveShelterAssignment(ShelterAssignmentId.of(shelterAssignmentId));

        return ValueResponse.success(modelMapper.toResponse(shelterAssignment), HttpStatus.OK);
    }

    @Override
    public ValueResponse<ShelterAssignmentResponse> cancelShelterAssignment(Long shelterAssignmentId, ShelterAssignmentCancelReason rejectReason) {
        ShelterAssignment shelterAssignment = useCase
                .cancelShelterAssignment(ShelterAssignmentCancel.of(shelterAssignmentId, rejectReason.cancelReason()));

        return ValueResponse.success(modelMapper.toResponse(shelterAssignment), HttpStatus.OK);
    }

    @Override
    public ValueResponse<ShelterAssignmentResponse> completeShelterAssignment(Long shelterAssignmentId) {
        ShelterAssignment shelterAssignment = useCase.completeShelterAssignment(ShelterAssignmentId.of(shelterAssignmentId));

        return ValueResponse.success(modelMapper.toResponse(shelterAssignment), HttpStatus.OK);
    }
}

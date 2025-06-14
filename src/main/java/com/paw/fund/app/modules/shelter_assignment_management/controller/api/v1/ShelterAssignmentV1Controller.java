package com.paw.fund.app.modules.shelter_assignment_management.controller.api.v1;

import com.paw.fund.app.modules.shelter_assignment_management.ShelterAssignmentModuleConstant;
import com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.IShelterAssignmentModelMapper;
import com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.ShelterAssignmentListRequest;
import com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.ShelterAssignmentResponse;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentCreateList;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentFilter;
import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentSearchCriteria;
import com.paw.fund.app.modules.shelter_assignment_management.service.usecase.IShelterAssignmentUseCase;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.response.ListResponse;
import com.paw.fund.utils.response.Meta;
import com.paw.fund.utils.response.PageResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterAssignmentV1Controller implements IShelterAssignmentV1API {
    @NonNull
    IShelterAssignmentUseCase useCase;

    @NonNull
    IShelterAssignmentModelMapper modelMapper;

    @Override
    public ListResponse<ShelterAssignmentResponse> createShelterAssignmentList(Long petIntakeRegistrationId, ShelterAssignmentListRequest request) {
        ShelterAssignmentCreateList shelterAssignmentCreateList = ShelterAssignmentCreateList.builder()
                .shelterIds(request.shelterIds())
                .petIntakeRegistrationId(petIntakeRegistrationId)
                .build();
        List<ShelterAssignmentResponse> shelterAssignments = useCase.createShelterAssignmentList(shelterAssignmentCreateList)
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(shelterAssignments, HttpStatus.CREATED);
    }

    @Override
    public PageResponse<ShelterAssignmentResponse> getShelterAssignmentList(Long shelterId,
                                                                            Long petIntakeRegistrationId,
                                                                            List<LocalDateTime> timeRange,
                                                                            List<String> statusCodes,
                                                                            String sorter, Integer current, Integer pageSize) {
        ShelterAssignmentSearchCriteria searchCriteria = ShelterAssignmentSearchCriteria
                .of(shelterId, petIntakeRegistrationId, timeRange, statusCodes);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize, sorter);
        Page<ShelterAssignmentResponse> responses = useCase
                .getShelterAssignmentList(ShelterAssignmentFilter.of(searchCriteria, pageRequestCustom))
                .map(modelMapper::toResponse);

        return PageResponse.success(
                responses.getContent(),
                Meta.of(responses),
                HttpStatus.OK
        );
    }
}

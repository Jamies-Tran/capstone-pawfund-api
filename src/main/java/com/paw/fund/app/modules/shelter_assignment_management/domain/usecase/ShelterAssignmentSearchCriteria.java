package com.paw.fund.app.modules.shelter_assignment_management.domain.usecase;

import com.paw.fund.utils.validation.ValidationUtil;
import lombok.Builder;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Builder
public record ShelterAssignmentSearchCriteria(
        Long shelterId,
        Long petIntakeRegistrationId,
        List<LocalDateTime> timeRange,
        List<String> statusCodes
) {
    public static ShelterAssignmentSearchCriteria of(Long shelterId,
                                                     Long petIntakeRegistrationId,
                                                     List<LocalDateTime> timeRange,
                                                     List<String> statusCodes) {
        return ShelterAssignmentSearchCriteria.builder()
                .shelterId(shelterId)
                .petIntakeRegistrationId(petIntakeRegistrationId)
                .timeRange(ValidationUtil.validateNotNullOrDefaultTimeRange(timeRange))
                .statusCodes(statusCodes)
                .build();
    }

    public static ShelterAssignmentSearchCriteria ofEmpty() {
        return ShelterAssignmentSearchCriteria.builder()
                .shelterId(null)
                .petIntakeRegistrationId(null)
                .timeRange(ValidationUtil.validateNotNullOrDefaultTimeRange(List.of()))
                .statusCodes(List.of())
                .build();
    }

    public static ShelterAssignmentSearchCriteria ofDefault(Long shelterId) {
        return ShelterAssignmentSearchCriteria.builder()
                .shelterId(shelterId)
                .petIntakeRegistrationId(null)
                .timeRange(ValidationUtil.validateNotNullOrDefaultTimeRange(List.of()))
                .statusCodes(List.of())
                .build();
    }

    public Boolean isShelterIdNullOrEmpty() {
        return Objects.isNull(shelterId);
    }

    public Boolean isPetIntakeRegistrationIdNullOrEmpty() {
        return Objects.isNull(petIntakeRegistrationId);
    }

    public Boolean isStatusCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(statusCodes);
    }
}

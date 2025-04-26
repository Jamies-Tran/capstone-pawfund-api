package com.paw.fund.app.modules.shelter_management.domain.usecase.registration;

import lombok.Builder;
import lombok.With;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Builder
public record ShelterRegistrationSearchCriteria(
        @With Long processById,
        List<LocalDateTime> requestAtTimeRange,
        List<LocalDateTime> receivedAtTimeRange,
        List<LocalDateTime> approvedAtTimeRange,
        List<LocalDateTime> rejectedAtTimeRange,
        List<String> statusCodes
) {
    public static ShelterRegistrationSearchCriteria of(
            List<LocalDateTime> requestAtTimeRange,
            List<LocalDateTime> receivedAtTimeRange,
            List<LocalDateTime> approvedAtTimeRange,
            List<LocalDateTime> rejectedAtTimeRange,
            List<String> statusCodes
    ) {
        return ShelterRegistrationSearchCriteria.builder()
                .requestAtTimeRange(requestAtTimeRange)
                .receivedAtTimeRange(receivedAtTimeRange)
                .approvedAtTimeRange(approvedAtTimeRange)
                .rejectedAtTimeRange(rejectedAtTimeRange)
                .statusCodes(statusCodes)
                .build();
    }

    public LocalDateTime requestAtFrom() {
        if(!CollectionUtils.isEmpty(requestAtTimeRange)) {
            return requestAtTimeRange.getFirst();
        }

        return null;
    }

    public LocalDateTime requestAtTo() {
        if(!CollectionUtils.isEmpty(requestAtTimeRange)) {
            return requestAtTimeRange.getLast();
        }

        return null;
    }

    public LocalDateTime receivedAtFrom() {
        if(!CollectionUtils.isEmpty(receivedAtTimeRange)) {
            return receivedAtTimeRange.getFirst();
        }

        return null;
    }

    public LocalDateTime receivedAtTo() {
        if(!CollectionUtils.isEmpty(receivedAtTimeRange)) {
            return receivedAtTimeRange.getLast();
        }

        return null;
    }

    public LocalDateTime approvedAtFrom() {
        if(!CollectionUtils.isEmpty(approvedAtTimeRange)) {
            return approvedAtTimeRange.getFirst();
        }

        return null;
    }

    public LocalDateTime approvedAtTo() {
        if(!CollectionUtils.isEmpty(approvedAtTimeRange)) {
            return approvedAtTimeRange.getLast();
        }

        return null;
    }

    public LocalDateTime rejectedAtFrom() {
        if(!CollectionUtils.isEmpty(rejectedAtTimeRange)) {
            return rejectedAtTimeRange.getFirst();
        }

        return null;
    }

    public LocalDateTime rejectedAtTo() {
        if(!CollectionUtils.isEmpty(rejectedAtTimeRange)) {
            return rejectedAtTimeRange.getLast();
        }

        return null;
    }

    public Boolean isRequestAtTimeRangeNullOrEmpty() {
        return CollectionUtils.isEmpty(requestAtTimeRange);
    }

    public Boolean isReceivedAtTimeRangeNullOrEmpty() {
        return CollectionUtils.isEmpty(receivedAtTimeRange);
    }

    public Boolean isApprovedAtTimeRangeNullOrEmpty() {
        return CollectionUtils.isEmpty(approvedAtTimeRange);
    }

    public Boolean isRejectedAtTimeRangeNullOrEmpty() {
        return CollectionUtils.isEmpty(rejectedAtTimeRange);
    }

    public Boolean isStatusCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(statusCodes);
    }

    public Boolean isProcessByIdNullOrEmpty() {
        return Objects.isNull(processById);
    }
}

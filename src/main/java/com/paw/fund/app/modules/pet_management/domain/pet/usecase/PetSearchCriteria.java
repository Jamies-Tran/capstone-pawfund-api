package com.paw.fund.app.modules.pet_management.domain.pet.usecase;

import com.paw.fund.utils.validation.ValidationUtil;
import lombok.Builder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Builder
public record PetSearchCriteria(
        String search,
        Long shelterId,
        List<LocalDateTime> timeRange,
        List<LocalDate> receivedAtTimeRange,
        List<LocalDate> dateOfBirthTimeRange,
        List<String> petTypeCodes,
        List<String> petBreedCodes,
        List<String> petHobbyCodes,
        List<String> statusCodes,
        List<String> receiveSourceCodes
) {
    public static PetSearchCriteria of(String search,
                                       Long shelterId,
                                       List<LocalDateTime> timeRange,
                                       List<LocalDate> receivedAtTimeRange,
                                       List<LocalDate> dateOfBirthTimeRange,
                                       List<String> petTypeCodes,
                                       List<String> petBreedCodes,
                                       List<String> petHobbyCodes,
                                       List<String> statusCodes,
                                       List<String> receiveSourceCodes) {
        return PetSearchCriteria.builder()
                .search(search)
                .shelterId(shelterId)
                .timeRange(ValidationUtil.validateNotNullOrDefaultTimeRange(timeRange))
                .receivedAtTimeRange(receivedAtTimeRange)
                .dateOfBirthTimeRange(dateOfBirthTimeRange)
                .petTypeCodes(petTypeCodes)
                .petBreedCodes(petBreedCodes)
                .petHobbyCodes(petHobbyCodes)
                .statusCodes(statusCodes)
                .receiveSourceCodes(receiveSourceCodes)
                .build();
    }

    public Boolean isSearchNullOrEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean isShelterIdNullOrEmpty() {
        return Objects.isNull(shelterId);
    }

    public Boolean isReceivedAtTimeRangeNullOrTimeRange() {
        return CollectionUtils.isEmpty(receivedAtTimeRange);
    }

    public Boolean isDateOfBirthTimeRangeNullOrTimeRange() {
        return CollectionUtils.isEmpty(dateOfBirthTimeRange);
    }

    public Boolean isStatusCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(statusCodes);
    }

    public Boolean isPetTypeCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(petTypeCodes);
    }

    public Boolean isPetBreedCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(petBreedCodes);
    }

    public Boolean isPetHobbyCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(petHobbyCodes);
    }

    public Boolean isReceiveSourceCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(receiveSourceCodes);
    }

    public LocalDate getReceivedAtMin() {
        if(CollectionUtils.isEmpty(receivedAtTimeRange)) {
            return null;
        }

        return receivedAtTimeRange.getFirst();
    }

    public LocalDate getReceivedAtMax() {
        if(CollectionUtils.isEmpty(receivedAtTimeRange)) {
            return null;
        }

        return receivedAtTimeRange.getLast();
    }

    public LocalDate getDateOfBirthMin() {
        if(CollectionUtils.isEmpty(dateOfBirthTimeRange)) {
            return null;
        }

        return dateOfBirthTimeRange.getFirst();
    }

    public LocalDate getDateOfBirthMax() {
        if(CollectionUtils.isEmpty(dateOfBirthTimeRange)) {
            return null;
        }

        return dateOfBirthTimeRange.getLast();
    }
}

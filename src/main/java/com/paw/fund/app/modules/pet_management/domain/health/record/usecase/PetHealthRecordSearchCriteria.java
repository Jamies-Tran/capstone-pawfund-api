package com.paw.fund.app.modules.pet_management.domain.health.record.usecase;

import com.paw.fund.app.modules.pet_management.domain.health.record.PetHealthRecord;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.Builder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PetHealthRecordSearchCriteria(
        Long petId,
        String diagnosisSearch,
        String treatmentSearch,
        List<LocalDateTime> timeRange,
        List<LocalDateTime> checkupDateTimeRange,
        List<String> healthStatusCodes
) {
    public static PetHealthRecordSearchCriteria of(Long petId,
                                                   String diagnosisSearch,
                                                   String treatmentSearch,
                                                   List<LocalDateTime> timeRange,
                                                   List<LocalDateTime> checkupDateTimeRange,
                                                   List<String> healthStatusCodes) {
        return PetHealthRecordSearchCriteria.builder()
                .petId(petId)
                .diagnosisSearch(diagnosisSearch)
                .treatmentSearch(treatmentSearch)
                .timeRange(ValidationUtil.validateNotNullOrDefaultTimeRange(timeRange))
                .checkupDateTimeRange(checkupDateTimeRange)
                .healthStatusCodes(healthStatusCodes)
                .build();
    }

    public Boolean isDiagnosisSearchNullOrEmpty() {
        return !StringUtils.hasText(diagnosisSearch);
    }

    public Boolean isTreatmentSearchNullOrEmpty() {
        return !StringUtils.hasText(treatmentSearch);
    }

    public Boolean isCheckupDateTimeRangeNullOrEmpty() {
        return CollectionUtils.isEmpty(checkupDateTimeRange);
    }

    public Boolean isHealthStatusCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(healthStatusCodes);
    }

    public LocalDateTime getCheckupDateTimeRangeMin() {
        if(CollectionUtils.isEmpty(checkupDateTimeRange)) {
            return null;
        }

        return checkupDateTimeRange().getFirst();
    }

    public LocalDateTime getCheckupDateTimeRangeMax() {
        if(CollectionUtils.isEmpty(checkupDateTimeRange)) {
            return null;
        }

        return checkupDateTimeRange().getLast();
    }
}

package com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase;

import com.paw.fund.utils.validation.ValidationUtil;
import lombok.Builder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PetIntakeRegistrationSearchCriteria(
        String search,
        List<LocalDateTime> timeRange,
        List<String> petTypeCodes,
        List<String> reasonTypeCodes,
        List<String> statusCodes
) {
    public static PetIntakeRegistrationSearchCriteria of(String search,
                                                         List<LocalDateTime> timeRange,
                                                         List<String> petTypeCodes,
                                                         List<String> reasonTypeCodes,
                                                         List<String> statusCodes) {
        return PetIntakeRegistrationSearchCriteria.builder()
                .search(search)
                .timeRange(ValidationUtil.validateNotNullOrDefaultTimeRange(timeRange))
                .petTypeCodes(petTypeCodes)
                .reasonTypeCodes(reasonTypeCodes)
                .statusCodes(statusCodes)
                .build();
    }

    public Boolean isSearchNullOrEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean isPetTypeCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(petTypeCodes);
    }

    public Boolean isReasonTypeCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(reasonTypeCodes);
    }

    public Boolean isStatusCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(statusCodes);
    }
}

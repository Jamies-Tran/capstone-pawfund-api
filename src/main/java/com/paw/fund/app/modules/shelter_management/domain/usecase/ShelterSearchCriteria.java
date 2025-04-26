package com.paw.fund.app.modules.shelter_management.domain.usecase;

import com.paw.fund.utils.validation.ValidationUtil;
import lombok.Builder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ShelterSearchCriteria(
        String search,
        List<String> statusCodes,
        List<LocalDateTime> timeRange
) {
    public static ShelterSearchCriteria of(String search,
                                           List<String> statusCodes,
                                           List<LocalDateTime> timeRange) {
        return ShelterSearchCriteria.builder()
                .search(search)
                .statusCodes(statusCodes)
                .timeRange(ValidationUtil.validateNotNullOrDefaultTimeRange(timeRange))
                .build();
    }

    public Boolean isSearchNullOrEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean isStatusCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(statusCodes);
    }
}

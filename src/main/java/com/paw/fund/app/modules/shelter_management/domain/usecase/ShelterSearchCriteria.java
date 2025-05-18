package com.paw.fund.app.modules.shelter_management.domain.usecase;

import com.paw.fund.utils.validation.ValidationUtil;
import lombok.Builder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Builder
public record ShelterSearchCriteria(
        String search,
        BigDecimal latitude,
        BigDecimal longitude,
        BigDecimal radius,
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

    public static ShelterSearchCriteria of(String search,
                                           BigDecimal latitude,
                                           BigDecimal longitude,
                                           BigDecimal radius,
                                           List<String> statusCodes,
                                           List<LocalDateTime> timeRange) {
        return ShelterSearchCriteria.builder()
                .search(search)
                .latitude(latitude)
                .longitude(longitude)
                .radius(radius)
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

    public Boolean isRadiusNullOrEmpty() {
        return Objects.isNull(radius);
    }
}

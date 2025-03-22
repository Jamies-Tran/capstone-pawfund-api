package com.paw.fund.app.modules.account_management.domain.usecase;

import com.paw.fund.utils.validation.ValidationUtil;
import lombok.Builder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AccountSearchCriteria(
        String search,
        List<LocalDateTime> timeRange,
        List<LocalDate> dayOfBirth,
        List<String> statusCodes,
        List<String> roleCodes,
        List<String> genderCodes
) {
    public static AccountSearchCriteria of(String search,
                                           List<LocalDateTime> timeRange,
                                           List<LocalDate> dayOfBirth,
                                           List<String> statusCodes,
                                           List<String> roleCodes,
                                           List<String> genderCodes) {
        return AccountSearchCriteria.builder()
                .search(search)
                .timeRange(ValidationUtil.validateNotNullOrDefaultTimeRange(timeRange))
                .dayOfBirth(dayOfBirth)
                .statusCodes(statusCodes)
                .roleCodes(roleCodes)
                .genderCodes(genderCodes)
                .build();
    }

    public LocalDate dayOfBirthFrom() {
        if(CollectionUtils.isEmpty(dayOfBirth)) {
            return null;
        }

        return dayOfBirth.getFirst();
    }

    public LocalDate dayOfBirthTo() {
        if(CollectionUtils.isEmpty(dayOfBirth) || dayOfBirth.size() < 2) {
            return null;
        }

        return dayOfBirth.get(1);
    }

    public Boolean isSearchEmptyOrNull() {
        return !StringUtils.hasText(search);
    }

    public Boolean isDayOfBirthEmptyOrNull() {
        return CollectionUtils.isEmpty(dayOfBirth);
    }

    public Boolean isStatusCodesEmptyOrNull() {
        return CollectionUtils.isEmpty(statusCodes);
    }

    public Boolean isRoleCodesEmptyOrNull() {
        return CollectionUtils.isEmpty(roleCodes);
    }

    public Boolean isGenderCodesEmptyOrNull() {
        return CollectionUtils.isEmpty(genderCodes);
    }
}

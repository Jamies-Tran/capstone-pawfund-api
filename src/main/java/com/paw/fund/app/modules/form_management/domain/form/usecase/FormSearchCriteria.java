package com.paw.fund.app.modules.form_management.domain.form.usecase;

import com.paw.fund.utils.validation.ValidationUtil;
import lombok.Builder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record FormSearchCriteria(
        String search,
        List<String> formTypeCodes,
        List<LocalDateTime> timeRange
) {
    public static FormSearchCriteria of(
            String search,
            List<String> formTypeCodes,
            List<LocalDateTime> timeRange
    ) {
        return FormSearchCriteria.builder()
                .search(search)
                .formTypeCodes(formTypeCodes)
                .timeRange(ValidationUtil.validateNotNullOrDefaultTimeRange(timeRange))
                .build();
    }

    public Boolean isSearchEmptyOrNull() {
        return !StringUtils.hasText(search);
    }

    public Boolean isFormTypeCodeEmptyOrNull() {
        return CollectionUtils.isEmpty(formTypeCodes);
    }
}

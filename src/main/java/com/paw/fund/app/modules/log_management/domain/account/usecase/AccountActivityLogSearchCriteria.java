package com.paw.fund.app.modules.log_management.domain.account.usecase;

import com.paw.fund.utils.validation.ValidationUtil;
import lombok.Builder;
import lombok.With;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Builder
public record AccountActivityLogSearchCriteria(
        @With Long accountId,
        String search,
        List<String> actionCodes,
        List<LocalDateTime> timeRange
) {
    public static AccountActivityLogSearchCriteria of(Long accountId, String search, List<String> actionCodes,
                                                      List<LocalDateTime> timeRange) {
        return AccountActivityLogSearchCriteria.builder()
                .accountId(accountId)
                .search(search)
                .actionCodes(actionCodes)
                .timeRange(ValidationUtil.validateNotNullOrDefaultTimeRange(timeRange))
                .build();
    }

    public static AccountActivityLogSearchCriteria of(String search, List<String> actionCodes, List<LocalDateTime> timeRange) {
        return AccountActivityLogSearchCriteria.builder()
                .search(search)
                .actionCodes(actionCodes)
                .timeRange(ValidationUtil.validateNotNullOrDefaultTimeRange(timeRange))
                .build();
    }

    public Boolean isAccountIdEmpty() {
        return Objects.isNull(accountId);
    }

    public Boolean isSearchEmpty() {
        return !StringUtils.hasText(search);
    }

    public Boolean isActionCodesEmpty() {
        return CollectionUtils.isEmpty(actionCodes);
    }
}

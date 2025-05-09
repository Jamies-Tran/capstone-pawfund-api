package com.paw.fund.app.modules.log_management.domain.pet.usecase;

import com.paw.fund.utils.validation.ValidationUtil;
import lombok.Builder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PetActivityLogSearchCriteria(
        String accountSearch,
        String descriptionSearch,
        List<LocalDateTime> timeRange,
        List<String> actionCodes
) {
    public static PetActivityLogSearchCriteria of(String accountSearch,
                                                  String descriptionSearch,
                                                  List<LocalDateTime> timeRange,
                                                  List<String> actionCodes) {
        return PetActivityLogSearchCriteria.builder()
                .accountSearch(accountSearch)
                .descriptionSearch(descriptionSearch)
                .timeRange(ValidationUtil.validateNotNullOrDefaultTimeRange(timeRange))
                .actionCodes(actionCodes)
                .build();
    }

    public Boolean isAccountSearchNullOrEmpty() {
        return !StringUtils.hasText(accountSearch);
    }

    public Boolean isDescriptionSearchNullOrEmpty() {
        return !StringUtils.hasText(descriptionSearch);
    }

    public Boolean isActionCodesNullOrEmpty() {
        return CollectionUtils.isEmpty(actionCodes);
    }

}

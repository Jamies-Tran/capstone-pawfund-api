package com.paw.fund.app.modules.log_management.domain.account.usecase;

import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;
import lombok.With;

@Builder
public record AccountActivityLogFilter(
        @With AccountActivityLogSearchCriteria searchCriteria,
        PageRequestCustom pageRequestCustom
) {
    public static AccountActivityLogFilter of(AccountActivityLogSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return AccountActivityLogFilter.builder()
                .searchCriteria(searchCriteria)
                .pageRequestCustom(pageRequestCustom)
                .build();
    }

    public AccountActivityLogFilter ofSelfLog(Long accountId) {
        return this.withSearchCriteria(this.searchCriteria.withAccountId(accountId));
    }
}

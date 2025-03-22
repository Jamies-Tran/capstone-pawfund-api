package com.paw.fund.app.modules.account_management.domain.usecase;

import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;

@Builder
public record AccountFilter(
        AccountSearchCriteria searchCriteria,
        PageRequestCustom pageRequestCustom
) {
}

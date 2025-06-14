package com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer;

import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;

@Builder
public record AccountFilter(
        AccountSearchCriteria searchCriteria,
        PageRequestCustom pageRequestCustom
) {
}

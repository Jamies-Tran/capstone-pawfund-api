package com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer;

import java.util.Map;

public record AccountIdAndThumbnail(
        Long accountId,
        String thumbnail
) {
}

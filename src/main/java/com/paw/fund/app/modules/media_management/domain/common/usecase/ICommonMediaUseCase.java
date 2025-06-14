package com.paw.fund.app.modules.media_management.domain.common.usecase;

import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountId;
import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.media_management.domain.common.event.listener.CreateCommonMediaListener;

import java.util.List;

public interface ICommonMediaUseCase {
    void createCommonMediaList(CreateCommonMediaListener eventListener);

    List<CommonMedia> getCommonMediaListByAccountId(AccountId accountId);
}

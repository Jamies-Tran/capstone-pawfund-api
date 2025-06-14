package com.paw.fund.app.modules.media_management.service.common;

import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountId;
import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.media_management.domain.common.event.listener.CreateCommonMediaListener;
import com.paw.fund.app.modules.media_management.domain.common.usecase.ICommonMediaUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommonMediaUseCaseService implements ICommonMediaUseCase {
    @NonNull
    CommonMediaCommandService commandService;

    @NonNull
    CommonMediaQueryService queryService;

    @Override
    @Transactional
    @EventListener
    public void createCommonMediaList(CreateCommonMediaListener eventListener) {
        commandService.saveAllWithAccountId(eventListener.getRequestId(), eventListener.getMedias());
    }

    @Override
    public List<CommonMedia> getCommonMediaListByAccountId(AccountId accountId) {
        return queryService.findAllByAccountId(accountId.value());
    }
}

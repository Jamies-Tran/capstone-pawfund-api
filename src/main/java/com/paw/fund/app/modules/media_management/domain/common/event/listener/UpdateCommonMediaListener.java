package com.paw.fund.app.modules.media_management.domain.common.event.listener;

import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateCommonMediaListener extends ApplicationEvent {
    Long accountId;
    List<CommonMedia> medias;

    public UpdateCommonMediaListener(Object source, Long accountId, List<CommonMedia> medias) {
        super(source);
        this.accountId = accountId;
        this.medias = medias;
    }
}

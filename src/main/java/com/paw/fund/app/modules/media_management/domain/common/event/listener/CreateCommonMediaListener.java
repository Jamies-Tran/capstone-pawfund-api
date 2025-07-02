package com.paw.fund.app.modules.media_management.domain.common.event.listener;

import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCommonMediaListener extends ApplicationEvent {
    Long requestId;
    List<CommonMedia> medias;

    public CreateCommonMediaListener(Object source, Long requestId, List<CommonMedia> medias) {
        super(source);
        this.requestId = requestId;
        this.medias = medias;
    }
}

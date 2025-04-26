package com.paw.fund.app.modules.form_management.domain.form.usecase;

import com.paw.fund.app.modules.form_management.domain.form.reply.FormReply;
import lombok.Builder;

@Builder
public record FormReplyUpdate(
        Long formResponseId,
        FormReply formReply
) {
    public static FormReplyUpdate of(Long formResponseId,
                                     FormReply formReply) {
        return FormReplyUpdate.builder()
                .formResponseId(formResponseId)
                .formReply(formReply)
                .build();
    }
}

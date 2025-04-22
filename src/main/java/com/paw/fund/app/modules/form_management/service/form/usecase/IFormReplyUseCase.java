package com.paw.fund.app.modules.form_management.service.form.usecase;

import com.paw.fund.app.modules.form_management.domain.form.reply.FormReply;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormReplyId;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormReplyUpdate;

public interface IFormReplyUseCase {
    FormReply createFormReply(FormReply formReply);

    FormReply getFormReplyDetail(FormReplyId formReplyId);

    FormReply updateFormReply(FormReplyUpdate formReplyUpdate);
}

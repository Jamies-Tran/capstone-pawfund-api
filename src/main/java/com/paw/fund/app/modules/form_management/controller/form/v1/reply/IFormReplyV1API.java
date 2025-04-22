package com.paw.fund.app.modules.form_management.controller.form.v1.reply;

import com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply.FormReplyRequest;
import com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply.FormReplyResponse;
import com.paw.fund.utils.response.ValueResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/form-reply")
public interface IFormReplyV1API {
    @PostMapping
    ValueResponse<FormReplyResponse> createFormReply(@RequestBody @Valid FormReplyRequest request);
}

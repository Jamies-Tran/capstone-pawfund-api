package com.paw.fund.app.modules.form_management.controller.form.v1.reply;

import com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply.FormReplyRequest;
import com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply.FormReplyResponse;
import com.paw.fund.utils.response.ValueResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/form-reply/{formResponseId}")
public interface IFormReplyPathV1API {
    @GetMapping
    ValueResponse<FormReplyResponse> getFormReplyDetail(@PathVariable Long formResponseId);

    @PutMapping
    ValueResponse<FormReplyResponse> updateFormReply(@PathVariable Long formResponseId,
                                                     @RequestBody @Valid FormReplyRequest formReplyRequest);
}

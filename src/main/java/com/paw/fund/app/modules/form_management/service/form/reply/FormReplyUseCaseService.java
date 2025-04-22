package com.paw.fund.app.modules.form_management.service.form.reply;

import com.paw.fund.app.modules.form_management.annotation.CreateAnswer;
import com.paw.fund.app.modules.form_management.annotation.GetFormReplyAdditionalData;
import com.paw.fund.app.modules.form_management.annotation.UpdateFormReplyAdditionalData;
import com.paw.fund.app.modules.form_management.domain.form.reply.FormReply;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormReplyId;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormReplyUpdate;
import com.paw.fund.app.modules.form_management.service.form.usecase.IFormReplyUseCase;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.dto.CurrentAccountLogin;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormReplyUseCaseService implements IFormReplyUseCase {
    @NonNull
    FormReplyCommandService commandService;

    @NonNull
    FormReplyQueryService queryService;

    @NonNull
    RequestContext requestContext;

    @Override
    @Transactional
    @CreateAnswer
    public FormReply createFormReply(FormReply formReply) {
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();

        return commandService.save(formReply.withAccountId(currentAccountLogin.accountId()));
    }

    @Override
    @GetFormReplyAdditionalData
    public FormReply getFormReplyDetail(FormReplyId formReplyId) {
        return queryService.findByFormResponseId(formReplyId.value());
    }

    @Override
    @Transactional
    @UpdateFormReplyAdditionalData
    public FormReply updateFormReply(FormReplyUpdate formReplyUpdate) {
        return commandService.update(formReplyUpdate.formResponseId(), formReplyUpdate.formReply());
    }


}

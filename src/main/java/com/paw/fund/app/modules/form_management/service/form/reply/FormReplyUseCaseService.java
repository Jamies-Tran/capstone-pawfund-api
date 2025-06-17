package com.paw.fund.app.modules.form_management.service.form.reply;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.account.usecase.IAccountUseCase;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountEmail;
import com.paw.fund.app.modules.form_management.aspect.CreateAnswer;
import com.paw.fund.app.modules.form_management.aspect.GetFormReplyAdditionalData;
import com.paw.fund.app.modules.form_management.aspect.UpdateFormReplyAdditionalData;
import com.paw.fund.app.modules.form_management.domain.form.reply.FormReply;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormReplyId;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormReplyUpdate;
import com.paw.fund.app.modules.form_management.service.form.usecase.IFormReplyUseCase;
import com.paw.fund.common.context.request.RequestContext;
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
    IAccountUseCase accountUseCase;

    @Override
    @Transactional
    @CreateAnswer
    public FormReply createFormReply(FormReply formReply) {
        Account account = accountUseCase.getAccountByEmail(
                AccountEmail.of(RequestContext.getCurrentAccountLogin()));

        return commandService.save(formReply.withAccountId(account.accountId()));
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

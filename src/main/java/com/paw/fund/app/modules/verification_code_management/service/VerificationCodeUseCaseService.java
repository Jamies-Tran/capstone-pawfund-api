package com.paw.fund.app.modules.verification_code_management.service;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.account_management.service.AccountQueryService;
import com.paw.fund.app.modules.log_management.domain.account.AccountActivityLog;
import com.paw.fund.app.modules.log_management.service.account.AccountActivityLogCommandService;
import com.paw.fund.app.modules.mail_sender.domain.MailSender;
import com.paw.fund.app.modules.mail_sender.service.MailSenderCommandService;
import com.paw.fund.app.modules.verification_code_management.domain.VerificationCode;
import com.paw.fund.app.modules.verification_code_management.domain.usecase.VerificationMail;
import com.paw.fund.app.modules.verification_code_management.service.usecase.IVerificationCodeUseCase;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.configuration.request.context.RequestContext;
import com.paw.fund.dto.CurrentAccountLogin;
import com.paw.fund.enums.EAction;
import com.paw.fund.enums.EAccountStatus;
import com.paw.fund.enums.EVerificationCodeType;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerificationCodeUseCaseService implements IVerificationCodeUseCase {
    @NonNull
    VerificationCodeCommandService commandService;

    @NonNull
    AccountQueryService accountQueryService;

    @NonNull
    MailSenderCommandService mailSenderCommandService;

    @NonNull
    AccountActivityLogCommandService accountActivityLogCommandService;

    @NonNull
    RequestContext requestContext;

    @NonFinal
    @Value("${app.mail.username}")
    String systemMail;

    @Transactional
    @Override
    public VerificationCode createAndSendCodeVerificationAccount(VerificationMail verificationMail) {
        ValidationUtil.validateNotNullPointerException(verificationMail);
        Account account = accountQueryService.findByAccountEmail(verificationMail.value());
        validateAccountVerification(account);

        VerificationCode verificationCode = VerificationCode.builder()
                .accountId(account.accountId())
                .typeCode(EVerificationCodeType.ACCOUNT_CREATION.getCode())
                .typeName(EVerificationCodeType.ACCOUNT_CREATION.getName())
                .build();
        VerificationCode createdVerificationCode = commandService.save(verificationCode);
        MailSender mailSender = MailSender.builder()
                .from(systemMail)
                .to(account.email())
                .content(createdVerificationCode.code())
                .isHTMLSupport(true)
                .build();
        mailSenderCommandService.sendMail(mailSender.prepareForAccountVerification(account.lastName()));
        AccountActivityLog log = AccountActivityLog.builder()
                .accountId(account.accountId())
                .actionCode(EAction.SEND_VERIFIED_ACCOUNT.getCode())
                .actionName(EAction.SEND_VERIFIED_ACCOUNT.getName())
                .build();
        accountActivityLogCommandService.save(log);

        return createdVerificationCode;
    }

    @Override
    public VerificationCode createAndSendCodeVerificationEmail(VerificationMail newEmail) {
        ValidationUtil.validateNotNullPointerException(newEmail);
        CurrentAccountLogin currentAccountLogin = requestContext.getCurrentAccountLogin();

        VerificationCode verificationCode = VerificationCode.builder()
                .accountId(currentAccountLogin.accountId())
                .newEmail(newEmail.value())
                .typeCode(EVerificationCodeType.EMAIL_UPDATE.getCode())
                .typeName(EVerificationCodeType.EMAIL_UPDATE.getName())
                .build();
        VerificationCode createdVerificationCode = commandService.save(verificationCode);
        MailSender mailSender = MailSender.builder()
                .from(systemMail)
                .to(newEmail.value())
                .content(createdVerificationCode.code())
                .isHTMLSupport(true)
                .build();
        mailSenderCommandService.sendMail(mailSender.prepareForEmailVerification(currentAccountLogin.fullName()));

        return createdVerificationCode;
    }

    private void validateAccountVerification(Account account) {
        if(!Objects.equals(account.statusCode(), EAccountStatus.INACTIVE.getCode())) {
            throw new ResourceNotValidException("Tài khoản không thể tạo mã xác nhận");
        }
    }
}

package com.paw.fund.app.modules.verification_management.service;

import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.account.usecase.IAccountUseCase;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountEmail;
import com.paw.fund.app.modules.log_management.domain.account.AccountActivityLog;
import com.paw.fund.app.modules.log_management.service.account.AccountActivityLogCommandService;
import com.paw.fund.app.modules.mail_management.domain.MailSender;
import com.paw.fund.app.modules.mail_management.service.MailSenderCommandService;
import com.paw.fund.app.modules.verification_management.domain.Verification;
import com.paw.fund.app.modules.verification_management.domain.event.listener.DeleteVerificationEventListener;
import com.paw.fund.app.modules.verification_management.domain.usecase.data.transfer.AccountIdAndName;
import com.paw.fund.app.modules.verification_management.domain.usecase.data.transfer.VerificationCriteria;
import com.paw.fund.app.modules.verification_management.domain.usecase.data.transfer.VerificationMail;
import com.paw.fund.app.modules.verification_management.domain.usecase.IVerificationUseCase;
import com.paw.fund.common.aspect.annotation.validate.args.ValidateArgs;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.common.context.request.RequestContext;
import com.paw.fund.common.CurrentAccountLogin;
import com.paw.fund.enums.EAccountAction;
import com.paw.fund.enums.EAccountStatus;
import com.paw.fund.enums.EVerificationType;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerificationUseCaseService implements IVerificationUseCase {
    @NonNull
    VerificationQueryService queryService;

    @NonNull
    VerificationCommandService commandService;

    @NonNull
    MailSenderCommandService mailSenderCommandService;

    @NonNull
    IAccountUseCase accountUseCase;

    @NonNull
    AccountActivityLogCommandService accountActivityLogCommandService;

    @NonNull
    RequestContext requestContext;

    @NonFinal
    @Value("${app.mail.username}")
    String systemMail;


    @Override
    @Transactional
    @ValidateArgs
    public Verification createAndSendCodeVerificationAccount(VerificationMail verificationMail) {
        Account account = accountUseCase.getAccountByEmail(AccountEmail.of(verificationMail.value()));
        validateAccountVerification(account);

        Verification verification = Verification.builder()
                .accountId(account.accountId())
                .typeCode(EVerificationType.ACCOUNT_CREATION.getCode())
                .typeName(EVerificationType.ACCOUNT_CREATION.getName())
                .build();
        Verification createdVerification = commandService.save(verification);
        MailSender mailSender = MailSender.builder()
                .from(systemMail)
                .to(account.email())
                .content(createdVerification.code())
                .isHTMLSupport(true)
                .build();
        mailSenderCommandService.sendMail(mailSender.prepareForAccountVerification(account.lastName()));
        AccountActivityLog log = AccountActivityLog.builder()
                .accountId(account.accountId())
                .actionCode(EAccountAction.SEND_VERIFIED_ACCOUNT.getCode())
                .actionName(EAccountAction.SEND_VERIFIED_ACCOUNT.getName())
                .build();
        accountActivityLogCommandService.save(log);

        return createdVerification;
    }

    @Override
    @Transactional
    @ValidateArgs
    public Verification createAndSendCodeVerificationEmail(VerificationMail newEmail) {
        AccountIdAndName account = queryService.findAccountIdAndNameByEmail(
                RequestContext.getCurrentAccountLogin());

        Verification verification = Verification.builder()
                .accountId(account.accountId())
                .newEmail(newEmail.value())
                .typeCode(EVerificationType.EMAIL_UPDATE.getCode())
                .typeName(EVerificationType.EMAIL_UPDATE.getName())
                .build();
        Verification createdVerification = commandService.save(verification);
        MailSender mailSender = MailSender.builder()
                .from(systemMail)
                .to(newEmail.value())
                .content(createdVerification.code())
                .isHTMLSupport(true)
                .build();
        mailSenderCommandService.sendMail(mailSender.prepareForEmailVerification(account.fullName()));

        return createdVerification;
    }

    @Override
    @ValidateArgs
    public Verification getVerificationCodeByCriteria(VerificationCriteria criteria) {
        return queryService.findByCodeAndAccountIdAndVerificationCodeType(
                criteria.code(),
                criteria.accountId(),
                criteria.verificationType()
        );
    }

    @Override
    @Transactional
    @EventListener
    @ValidateArgs
    public void deleteVerificationByCode(DeleteVerificationEventListener eventListener) {
        commandService.deleteByCode(eventListener.getVerificationCode());
    }


    private void validateAccountVerification(Account account) {
        if(!Objects.equals(account.statusCode(), EAccountStatus.INACTIVE.getCode())) {
            throw new ResourceNotValidException("Tài khoản không thể tạo mã xác nhận");
        }
    }
}

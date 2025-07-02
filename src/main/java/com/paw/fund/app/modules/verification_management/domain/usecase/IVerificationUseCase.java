package com.paw.fund.app.modules.verification_management.domain.usecase;

import com.paw.fund.app.modules.verification_management.domain.Verification;
import com.paw.fund.app.modules.verification_management.domain.event.listener.DeleteVerificationEventListener;
import com.paw.fund.app.modules.verification_management.domain.usecase.data.transfer.VerificationCriteria;
import com.paw.fund.app.modules.verification_management.domain.usecase.data.transfer.VerificationMail;

public interface IVerificationUseCase {
    Verification createAndSendCodeVerificationAccount(VerificationMail verificationMail);

    Verification createAndSendCodeVerificationEmail(VerificationMail newEmail);

    Verification getVerificationCodeByCriteria(VerificationCriteria criteria);

    void deleteVerificationByCode(DeleteVerificationEventListener eventListener);
}

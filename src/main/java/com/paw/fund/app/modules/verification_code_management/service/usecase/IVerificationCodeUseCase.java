package com.paw.fund.app.modules.verification_code_management.service.usecase;

import com.paw.fund.app.modules.verification_code_management.domain.VerificationCode;
import com.paw.fund.app.modules.verification_code_management.domain.usecase.VerificationMail;

public interface IVerificationCodeUseCase {
    VerificationCode createAndSendCodeVerificationAccount(VerificationMail verificationMail);
}

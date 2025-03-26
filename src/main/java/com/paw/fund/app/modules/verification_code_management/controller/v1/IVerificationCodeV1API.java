package com.paw.fund.app.modules.verification_code_management.controller.v1;

import com.paw.fund.app.modules.verification_code_management.controller.models.VerificationEmailRequest;
import com.paw.fund.app.modules.verification_code_management.controller.models.VerificationCodeResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/verification-code")
@Tag(name = "Verification Code V1", description = "QL Mã xác thực")
public interface IVerificationCodeV1API {
    @PostMapping("/email-verification/send")
    @Operation(
            summary = "Gửi mã xác thực email qua email mới của người dùng",
            description = """
                    - Tạo và gửi mã xác thực email qua email mới của người dùng cung cấp
                    """)
    ValueResponse<VerificationCodeResponse> sendEmailVerification(
            @RequestBody
            @Valid
            VerificationEmailRequest verificationCodeRequest);
}

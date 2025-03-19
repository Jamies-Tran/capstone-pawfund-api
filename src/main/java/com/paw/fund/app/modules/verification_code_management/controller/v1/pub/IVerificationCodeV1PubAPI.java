package com.paw.fund.app.modules.verification_code_management.controller.v1.pub;

import com.paw.fund.app.modules.verification_code_management.controller.models.VerificationCodeResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/verification-code")
@Tag(name = "Verification Code", description = "QL Mã xác thực")
public interface IVerificationCodeV1PubAPI {
    @PostMapping("/send")
    @Operation(
            summary = "Gửi mã xác thực tài khoản qua email của người dùng",
            description = """
                    - Tạo và gửi mã xác thực tài khoản qua email người dùng cung cấp
                    """)
    ValueResponse<VerificationCodeResponse> sendAccountVerification(
            @Schema(description = "Email cần xác thực") @RequestParam String email);
}

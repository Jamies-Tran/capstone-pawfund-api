package com.paw.fund.app.modules.account_management.controller.v1.pub;

import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.verification.code.VerificationCodeRequest;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/public/account/{accountId}")
@Tag(name = "Account", description = "QL tài khoản")
public interface IAccountPathV1PubAPI {
    @PatchMapping("/verify")
    @Operation(
            summary = "Xác minh tài khoản",
            description = """
                    - Người dùng xác minh bang mã xác thực gửi qua mail
                    """)
    ValueResponse<AccountResponse> verifyCreatedAccount(
            @PathVariable Long accountId, @RequestBody @Valid VerificationCodeRequest request);
}

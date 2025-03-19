package com.paw.fund.app.modules.account_management.controller.v1.pub;

import com.paw.fund.app.modules.account_management.controller.models.AccountRequest;
import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/public/account")
@Tag(name = "Account", description = "QL tài khoản")
public interface IAccountV1PubAPI {
    @PostMapping
    @Operation(
            summary = "Tạo tài khoản",
            description = """
                    - Người dùng tạo tài khoản
                    """)
    ValueResponse<AccountResponse> createAccount(@RequestBody @Valid AccountRequest accountRequest);
}

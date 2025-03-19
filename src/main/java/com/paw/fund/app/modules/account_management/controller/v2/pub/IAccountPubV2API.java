package com.paw.fund.app.modules.account_management.controller.v2.pub;

import com.paw.fund.app.modules.account_management.controller.models.AccountRequest;
import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v2/public/account")
@Tag(name = "Account", description = "QL tài khoản")
public interface IAccountPubV2API {

    @PostMapping("/adopter-donor")
    @Operation(
            summary = "Tạo tài khoản cho người nhận nuôi và người quyên góp",
            description = """
                    - Người dùng tạo tài khoản
                    """)
    ValueResponse<AccountResponse> createAdopterAndDonor(@RequestBody @Valid AccountRequest request);
}

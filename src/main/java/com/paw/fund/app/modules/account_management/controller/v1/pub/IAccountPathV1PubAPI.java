package com.paw.fund.app.modules.account_management.controller.v1.pub;

import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/public/account/{accountId}")
@Tag(name = "Account V1", description = "QL tài khoản")
public interface IAccountPathV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Xem thông tin chi tiết của tài khoản",
            description = """
                    - Người dùng xem thông tin chi tiết của tài khoản
                    """)
    ValueResponse<AccountResponse> getAccountDetail(@PathVariable Long accountId);
}

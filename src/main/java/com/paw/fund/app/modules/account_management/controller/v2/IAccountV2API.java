package com.paw.fund.app.modules.account_management.controller.v2;

import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.AccountRoleRegisterRequest;
import com.paw.fund.app.modules.account_management.controller.models.AccountUpdateRequest;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v2/api/account")
@Tag(name = "Account V2", description = "QL tài khoản")
public interface IAccountV2API {
    @PatchMapping("/register-adoptor")
    ValueResponse<AccountResponse> registerAdopterRole(AccountRoleRegisterRequest request);
}

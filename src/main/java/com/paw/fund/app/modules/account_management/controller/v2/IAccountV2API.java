package com.paw.fund.app.modules.account_management.controller.v2;

import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.AccountRoleRegisterRequest;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v2/api/account")
@Tag(name = "Account V2", description = "QL tài khoản")
public interface IAccountV2API {
    @PutMapping("/register-adoptor")
    @Operation(
            summary = "Đăng ký quyền nhận nuôi thú cưng",
            description = """
                    - Người dùng đã xác thực đăng ký quyền nhận nuôi thú cưng
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    """)
    ValueResponse<AccountResponse> registerAdopterRole(@RequestBody @Valid AccountRoleRegisterRequest request);
}

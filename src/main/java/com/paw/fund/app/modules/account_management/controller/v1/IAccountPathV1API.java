package com.paw.fund.app.modules.account_management.controller.v1;

import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.AccountUpdatePasswordRequest;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/account/{accountId}")
@Tag(name = "Account", description = "QL tài khoản")
public interface IAccountPathV1API {
    @PatchMapping("/active")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Kích hoạt tài khoản",
            description = """
                    - Admin kích hoạt tài khoản
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<AccountResponse> activeAccount(@Schema(description = "Id tài khoản người dùng") @PathVariable Long accountId);

    @PatchMapping("/inactive")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Vô hiệu hóa tài khoản",
            description = """
                    - Admin vô hiệu hóa tài khoản
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<AccountResponse> inActiveAccount(
            @Schema(description = "Id tài khoản người dùng")
            @PathVariable
            Long accountId);

    @PatchMapping("/change-password")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Đổi mật khẩu",
            description = """
                    - Admin đổi mật khẩu của tài khoản người dùng
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<AccountResponse> changePassword(@PathVariable
                                                  Long accountId,
                                                  @RequestBody
                                                  @Valid
                                                  AccountUpdatePasswordRequest accountUpdatePasswordRequest);
}

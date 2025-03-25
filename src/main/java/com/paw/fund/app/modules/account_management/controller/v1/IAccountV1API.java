package com.paw.fund.app.modules.account_management.controller.v1;

import com.paw.fund.app.modules.account_management.controller.models.AccountRequest;
import com.paw.fund.app.modules.account_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.account_management.controller.models.AccountUpdatePasswordRequest;
import com.paw.fund.app.modules.account_management.controller.models.AccountUpdateRequest;
import com.paw.fund.app.modules.account_management.controller.models.verification.code.AccountVerificationCodeRequest;
import com.paw.fund.app.modules.account_management.controller.models.verification.code.EmailVerificationCodeRequest;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/account")
@Tag(name = "Account", description = "QL tài khoản")
public interface IAccountV1API {
    @PostMapping("/admin")
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @Operation(
            summary = "Tạo tài khoản Admin",
            description = """
                    - Admin tạo tài khoản admin
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<AccountResponse> createAdmin(@RequestBody @Valid AccountRequest accountRequest);

    @PostMapping("/staff")
    @PreAuthorize("hasRole({'ROLE_SHELTER_OWNER'})")
    @Operation(
            summary = "Tạo tài khoản Admin",
            description = """
                    - Chủ trung tâm cứu hộ tạo tài khoản nhân viên
                    - [SHELTER_OWNER - Chủ trung tâm cứu hộ]
                    """)
    ValueResponse<AccountResponse> createStaff(@RequestBody @Valid AccountRequest accountRequest);

    @PutMapping
    @Operation(
            summary = "Cập nhật tài khoản",
            description = """
                    - Người dùng cập nhật thông tin cá nhân của tài khoản
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    """)
    ValueResponse<AccountResponse> selfChangeInfo(@RequestBody @Valid AccountUpdateRequest updateRequest);

    @PatchMapping("/self-change-password")
    @Operation(
            summary = "Đổi mật khẩu",
            description = """
                    - Người dùng tự đổi mật khẩu của tài khoản
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    """)
    ValueResponse<AccountResponse> selfChangePassword(@RequestBody @Valid AccountUpdatePasswordRequest accountUpdatePasswordRequest);

    @PatchMapping("/email-verify")
    @Operation(
            summary = "Xác thực thay đổi email",
            description = """
                    - Người dùng đăng nhập xác thực cập nhật email
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    """)
    ValueResponse<AccountResponse> verifyEmail(
            @RequestBody
            @Valid
            EmailVerificationCodeRequest emailVerifyCodeRequest);

    @GetMapping("/self-detail")
    @Operation(
            summary = "Xem thông tin chi tiết của cá nhân",
            description = """
                    - Người dùng xem thông tin chi tiết của cá nhân
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    """)
    ValueResponse<AccountResponse> getSelfAccountDetail();
}

package com.paw.fund.app.modules.session_management.controller.v1.pub;

import com.paw.fund.app.modules.session_management.controller.models.LoginRequest;
import com.paw.fund.app.modules.session_management.controller.models.RefreshResponse;
import com.paw.fund.app.modules.session_management.controller.models.SessionResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/public/session")
@Tag(name = "Session V1", description = "QL phiên đăng nhập")
public interface ISessionV1PubAPI {
    @PostMapping("/login")
    @Operation(
            summary = "Đăng nhập",
            description = """
                    - Người dùng đăng nhập vào tài khoản trên hệ thống
                    """)
    ValueResponse<SessionResponse> login(@RequestBody @Valid LoginRequest request);

    @GetMapping("/{refreshToken}")
    @Operation(
            summary = "Refresh authorization",
            description = """
                    - Làm mới access token bằng refresh token
                    """)
    ValueResponse<RefreshResponse> refresh(@Schema(description = "Refresh token") @PathVariable String refreshToken);
}

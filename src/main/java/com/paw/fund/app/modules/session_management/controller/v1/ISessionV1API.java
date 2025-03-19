package com.paw.fund.app.modules.session_management.controller.v1;

import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/session")
@Tag(name = "Session", description = "QL phiên đăng nhập")
public interface ISessionV1API {
    @PutMapping("/logout")
    @Operation(
            summary = "Đăng xuất",
            description = """
                    - Người dùng đăng xuất khỏi hệ thống
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    """)
    ValueResponse<?> logout();
}

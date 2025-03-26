package com.paw.fund.app.modules.log_management.controller.account.v1;

import com.paw.fund.app.modules.log_management.controller.account.models.AccountActivityLogResponse;
import com.paw.fund.utils.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/api/account-activity-log")
@Tag(name = "Log V1", description = "QL nhật ký hoạt động")
public interface IAccountActivityLogV1API {
    @GetMapping("/self")
    @Operation(
            summary = "Xem danh sách nhật ký hoạt động của tài khoản",
            description = """
                    - Người dùng tự xem nhật ký hoạt động của mình
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    """)
    PageResponse<AccountActivityLogResponse> getSelfLog(
            @Schema(description = "Tìm kiếm theo tên thao tác")
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @ArraySchema(schema = @Schema(description = "Tìm kiếm thời gian ghi nhật ký trong khoản thời gian tìm kiếm"))
            @RequestParam(required = false, value = "timeRange", defaultValue = "")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> timeRange,
            @ArraySchema(schema = @Schema(description = "Tìm kiếm theo danh sách mã thao tác"))
            @RequestParam(required = false, value = "actionCodes", defaultValue = "")
            List<String> actionCodes,
            @Schema(description = "Sắp xếp record trả về (Mặc định sắp xếp theo thời gian ghi nhật ký)")
            @RequestParam(required = false, value = "sorter", defaultValue = "loggedAt_desc")
            String sorter,
            @Schema(description = "Tìm kiếm theo trang hiện tại (mặc định trang đầu tiên)")
            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,
            @Schema(description = "Số phần tử của 1 trang (mặc định 25 phần tử)")
            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );
}

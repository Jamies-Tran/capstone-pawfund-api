package com.paw.fund.app.modules.form_management.controller.form.v1;

import com.paw.fund.app.modules.form_management.controller.form.v1.models.form.FormRequest;
import com.paw.fund.app.modules.form_management.controller.form.v1.models.form.FormResponse;
import com.paw.fund.utils.response.PageResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/api/form")
@Tag(name = "Form V1", description = "QL form")
public interface IFormV1API {
    @PostMapping("/shelter-register")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Tạo form đăng ký shelter",
            description = """
                    - Admin tạo form đăng ký shelter
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<FormResponse> createShelterRegisterForm(@RequestBody FormRequest formRequest);

    @GetMapping
    @Operation(
            summary = "DS form",
            description = """
                    - Người dùng đã xác thực tìm kiếm DS form
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    - Note: Tìm kiếm theo danh sách loại form (Category loại form)
                    """)
    PageResponse<FormResponse> getFormList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            @Schema(description = "Tìm kiếm theo tiêu đề form")
            String search,

            @RequestParam(required = false, value = "timeRange", defaultValue = "")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> timeRange,

            @RequestParam(required = false, value = "formTypeCodes", defaultValue = "")
            List<String> formTypeCodes,

            @RequestParam(required = false, value = "sorter", defaultValue = "updatedAt")
            @Schema(description = "Sắp xêp theo tiêu chí (mặc định theo ngày tạo)")
            String sorter,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            @Schema(description = "Trang hiện tại (mặc định trang đầu tiên)")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            @Schema(description = "Số phần tử (mặc định 25)")
            Integer pageSize
    );
}

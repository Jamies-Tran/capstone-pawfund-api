package com.paw.fund.app.modules.license_management.controller;

import com.paw.fund.app.modules.license_management.controller.models.LicenseRequest;
import com.paw.fund.app.modules.license_management.controller.models.LicenseResponse;
import com.paw.fund.utils.response.PageResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/api/license")
@Tag(name = "License V1", description = "QL giấy phép hoạt động")
public interface ILicenseV1API {
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Tạo giấy phép",
            description = """
                    - Quản trị viên tạo giấy phép
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<LicenseResponse> createLicense(@RequestBody @Valid LicenseRequest request);

    @GetMapping
    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_SHELTER_OWNER'})")
    @Operation(
            summary = "DS giấy phép",
            description = """
                    - Quản trị viên | Chủ trung tâm cứu trợ xem ds giấy phép
                    - [ADMIN | SHELTER_OWNER - Quản trị viên | Chủ trung tâm cứu trợ]
                    """)
    PageResponse<LicenseResponse> getLicenseList(
            @RequestParam(required = false, value = "timeRange", defaultValue = "")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> timeRange,

            @RequestParam(required = false, value = "licenseTypeCodes", defaultValue = "")
            List<String> licenseTypeCodes,

            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,

            @RequestParam(required = false, value = "sorter", defaultValue = "updatedAt")
            @Schema(description = "Tiêu chí sắp xếp (mặc định theo thời gian cập nhật)")
            String sorter,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            @Schema(description = "Trang hiện tại (mặc định trang đầu tiên)")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            @Schema(description = "Số phân tử (mặc định 25)")
            Integer pageSize
    );
}

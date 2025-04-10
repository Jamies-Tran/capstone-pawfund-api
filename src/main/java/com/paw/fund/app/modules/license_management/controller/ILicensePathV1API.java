package com.paw.fund.app.modules.license_management.controller;

import com.paw.fund.app.modules.license_management.controller.models.LicenseResponse;
import com.paw.fund.app.modules.license_management.controller.models.LicenseUpdateRequest;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/license/{licenseId}")
@Tag(name = "License V1", description = "QL giấy phép hoạt động")
public interface ILicensePathV1API {
    @GetMapping
    @Operation(
            summary = "Xem chi tiết của giấy phép",
            description = """
                    - Người dùng đã xác thực xem chi tiết của giấy phép
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    """)
    ValueResponse<LicenseResponse> getLicenseDetail(
            @PathVariable
            @Schema(description = "ID giấy phép")
            Long licenseId);

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Cập nhật giấy phép",
            description = """
                    - Quản trị viên cập nhật giấy phép
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<LicenseResponse> updateLicense(
            @PathVariable
            @Schema(description = "ID giấy phép")
            Long licenseId,

            @RequestBody @Valid LicenseUpdateRequest request);

    @PatchMapping("/active")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Kích hoạt giấy phép",
            description = """
                    - Quản trị viên kích hoạt giấy phép
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<LicenseResponse> activeLicense(
            @PathVariable
            @Schema(description = "ID giấy phép")
            Long licenseId);

    @PatchMapping("/inactive")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Vô hiệu hóa giấy phép",
            description = """
                    - Quản trị viên vô hiệu hóa giấy phép
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<LicenseResponse> inactiveLicense(
            @PathVariable
            @Schema(description = "ID giấy phép")
            Long licenseId);

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Xóa giấy phép",
            description = """
                    - Quản trị viên xóa giấy phép
                    - [ADMIN - Quản trị viên]
                    """)
    void deleteLicense(
            @PathVariable
            @Schema(description = "ID giấy phép")
            Long licenseId);
}

package com.paw.fund.app.modules.license_management.controller;

import com.paw.fund.app.modules.license_management.controller.models.LicenseResponse;
import com.paw.fund.app.modules.license_management.controller.models.LicenseUpdateRequest;
import com.paw.fund.utils.response.ValueResponse;
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
    ValueResponse<LicenseResponse> getLicenseDetail(@PathVariable Long licenseId);

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ValueResponse<LicenseResponse> updateLicense(@PathVariable Long licenseId,
                                                 @RequestBody @Valid LicenseUpdateRequest request);

    @PatchMapping("/active")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ValueResponse<LicenseResponse> activeLicense(@PathVariable Long licenseId);

    @PatchMapping("/inactive")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ValueResponse<LicenseResponse> inactiveLicense(@PathVariable Long licenseId);

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteLicense(@PathVariable Long licenseId);
}

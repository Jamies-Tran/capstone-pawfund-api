package com.paw.fund.app.modules.license_management.controller;

import com.paw.fund.app.modules.license_management.controller.models.LicenseResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/license/{licenseId}")
@Tag(name = "License V1", description = "QL giấy phép hoạt động")
public interface ILicensePathV1API {
    @GetMapping
    ValueResponse<LicenseResponse> getLicenseDetail(@PathVariable Long licenseId);
}

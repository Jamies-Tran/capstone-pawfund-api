package com.paw.fund.app.modules.license_management.controller;

import com.paw.fund.app.modules.license_management.controller.models.LicenseRequest;
import com.paw.fund.app.modules.license_management.controller.models.LicenseResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/license")
@Tag(name = "License V1", description = "QL giấy phép hoạt động")
public interface ILicenseV1API {
    @PostMapping
    ValueResponse<LicenseResponse> createLicense(@RequestBody @Valid LicenseRequest request);
}

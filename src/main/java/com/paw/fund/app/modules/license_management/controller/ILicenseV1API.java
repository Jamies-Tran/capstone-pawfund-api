package com.paw.fund.app.modules.license_management.controller;

import com.paw.fund.app.modules.license_management.controller.models.LicenseRequest;
import com.paw.fund.app.modules.license_management.controller.models.LicenseResponse;
import com.paw.fund.utils.response.PageResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
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
    ValueResponse<LicenseResponse> createLicense(@RequestBody @Valid LicenseRequest request);

    @GetMapping
    PageResponse<LicenseResponse> getLicenseList(
            @RequestParam(required = false, value = "timeRange", defaultValue = "")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> timeRange,

            @RequestParam(required = false, value = "licenseTypeCodes", defaultValue = "")
            List<String> licenseTypeCodes,

            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,

            @RequestParam(required = false, value = "sorter", defaultValue = "updatedAt")
            String sorter,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );
}

package com.paw.fund.app.modules.license_management.controller;

import com.paw.fund.app.modules.license_management.controller.models.ILicenseModelMapper;
import com.paw.fund.app.modules.license_management.controller.models.LicenseRequest;
import com.paw.fund.app.modules.license_management.controller.models.LicenseResponse;
import com.paw.fund.app.modules.license_management.domain.License;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseFilter;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseSearchCriteria;
import com.paw.fund.app.modules.license_management.service.usecase.ILicenseUseCase;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.response.Meta;
import com.paw.fund.utils.response.PageResponse;
import com.paw.fund.utils.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseV1Controller implements ILicenseV1API {
    @NonNull
    ILicenseUseCase useCase;

    @NonNull
    ILicenseModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<LicenseResponse> createLicense(LicenseRequest request) {
        License savedLicense = useCase.createLicense(modelMapper.toDto(request));

        return ValueResponse.success(modelMapper.toResponse(savedLicense), HttpStatus.CREATED, API_VERSION);
    }

    @Override
    public PageResponse<LicenseResponse> getLicenseList(List<LocalDateTime> timeRange,
                                                        List<String> licenseTypeCodes,
                                                        List<String> statusCodes,
                                                        String sorter, Integer current, Integer pageSize) {
        LicenseSearchCriteria searchCriteria = LicenseSearchCriteria.of(timeRange, licenseTypeCodes, statusCodes);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize, sorter);
        Page<LicenseResponse> responses = useCase.getLicenseList(LicenseFilter.of(searchCriteria, pageRequestCustom))
                .map(modelMapper::toResponse);

        return PageResponse.success(
                responses.getContent(),
                Meta.of(responses),
                HttpStatus.OK,
                API_VERSION);
    }
}

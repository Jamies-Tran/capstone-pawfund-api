package com.paw.fund.app.modules.license_management.controller;

import com.paw.fund.app.modules.license_management.controller.models.ILicenseModelMapper;
import com.paw.fund.app.modules.license_management.controller.models.LicenseResponse;
import com.paw.fund.app.modules.license_management.domain.License;
import com.paw.fund.app.modules.license_management.domain.usecase.LicenseId;
import com.paw.fund.app.modules.license_management.service.usecase.ILicenseUseCase;
import com.paw.fund.utils.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicensePathV1Controller implements ILicensePathV1API {
    @NonNull
    ILicenseUseCase  useCase;

    @NonNull
    ILicenseModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<LicenseResponse> getLicenseDetail(Long licenseId) {
        License foundLicense = useCase.getLicenseDetail(LicenseId.of(licenseId));

        return ValueResponse.success(modelMapper.toResponse(foundLicense), HttpStatus.OK, API_VERSION);
    }
}

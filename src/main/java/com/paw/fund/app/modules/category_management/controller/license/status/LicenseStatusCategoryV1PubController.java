package com.paw.fund.app.modules.category_management.controller.license.status;

import com.paw.fund.app.modules.category_management.controller.license.models.status.ILicenseStatusCategoryModelMapper;
import com.paw.fund.app.modules.category_management.controller.license.models.status.LicenseStatusCategoryResponse;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.license.status.usecase.ILicenseStatusCategoryUseCase;
import com.paw.fund.utils.response.ListResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseStatusCategoryV1PubController implements ILicenseStatusCategoryV1PubAPI {
    @NonNull
    ILicenseStatusCategoryUseCase useCase;

    @NonNull
    ILicenseStatusCategoryModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ListResponse<LicenseStatusCategoryResponse> getLicenseStatusList(String search) {
        List<LicenseStatusCategoryResponse> responses = useCase.getLicenseTypeList(CategorySearch.of(search))
                .stream()
                .map(modelMapper::toResponse)
                .toList();

        return ListResponse.success(responses, HttpStatus.OK);
    }
}

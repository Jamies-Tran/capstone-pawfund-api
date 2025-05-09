package com.paw.fund.app.modules.category_management.service.license.status;

import com.paw.fund.app.modules.category_management.domain.LicenseStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.license.status.usecase.ILicenseStatusCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseStatusCategoryUseCaseService implements ILicenseStatusCategoryUseCase {
    @NonNull
    LicenseStatusCategoryQueryService queryService;

    @Override
    public List<LicenseStatusCategory> getLicenseTypeList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

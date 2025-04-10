package com.paw.fund.app.modules.category_management.service.license.type;

import com.paw.fund.app.modules.category_management.domain.LicenseTypeCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.usecase.ILicenseTypeCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseTypeCategoryUseCaseService implements ILicenseTypeCategoryUseCase {
    @NonNull
    LicenseTypeCategoryQueryService queryService;

    @Override
    public List<LicenseTypeCategory> getLicenseTypeList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

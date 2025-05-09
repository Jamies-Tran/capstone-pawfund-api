package com.paw.fund.app.modules.category_management.service.shelter.registration;

import com.paw.fund.app.modules.category_management.domain.ShelterRegistrationStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.shelter.registration.usecase.IShelterRegistrationStatusCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterRegistrationStatusCategoryUseCaseService implements IShelterRegistrationStatusCategoryUseCase {
    @NonNull
    ShelterRegistrationStatusCategoryQueryService queryService;

    @Override
    public List<ShelterRegistrationStatusCategory> getShelterRegistrationCategoryList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

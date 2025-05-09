package com.paw.fund.app.modules.category_management.service.pet.health.status;

import com.paw.fund.app.modules.category_management.domain.PetHealthStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.pet.health.status.usecase.IPetHealthStatusCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetHealthStatusUseCaseService implements IPetHealthStatusCategoryUseCase {
    @NonNull
    PetHealthStatusCategoryQueryService queryService;

    @Override
    public List<PetHealthStatusCategory> getPetHealthStatusCategoryList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

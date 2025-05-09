package com.paw.fund.app.modules.category_management.service.pet.status;

import com.paw.fund.app.modules.category_management.domain.PetStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.pet.status.usecase.IPetStatusCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetStatusCategoryUseCaseService implements IPetStatusCategoryUseCase {
    @NonNull
    PetStatusCategoryQueryService queryService;

    @Override
    public List<PetStatusCategory> getPetStatusCategoryList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

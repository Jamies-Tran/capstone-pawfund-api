package com.paw.fund.app.modules.category_management.service.pet.color;

import com.paw.fund.app.modules.category_management.domain.PetColorCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.pet.color.usecase.IPetColorCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetColorCategoryUseCaseService implements IPetColorCategoryUseCase {
    @NonNull
    PetColorCategoryQueryService queryService;

    @Override
    public List<PetColorCategory> getPetColorCategoryList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

package com.paw.fund.app.modules.category_management.service.pet.gender;

import com.paw.fund.app.modules.category_management.domain.PetGenderCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.pet.gender.usecase.IPetGenderCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetGenderCategoryUseCaseService implements IPetGenderCategoryUseCase {
    @NonNull
    PetGenderCategoryQueryService queryService;

    @Override
    public List<PetGenderCategory> getPetGenderCategoryList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

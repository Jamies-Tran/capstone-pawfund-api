package com.paw.fund.app.modules.category_management.service.pet.information.status;

import com.paw.fund.app.modules.category_management.domain.PetInformationStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.pet.information.status.usecase.IPetInformationStatusCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetInformationStatusCategoryUseCaseService implements IPetInformationStatusCategoryUseCase {
    @NonNull
    PetInformationStatusCategoryQueryService queryService;


    @Override
    public List<PetInformationStatusCategory> getPetInformationCategoryList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

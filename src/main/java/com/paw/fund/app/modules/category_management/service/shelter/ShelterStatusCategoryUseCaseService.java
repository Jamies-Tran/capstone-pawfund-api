package com.paw.fund.app.modules.category_management.service.shelter;

import com.paw.fund.app.modules.category_management.domain.ShelterStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.shelter.usecase.IShelterStatusCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterStatusCategoryUseCaseService implements IShelterStatusCategoryUseCase {
    @NonNull
    ShelterStatusCategoryQueryService queryService;

    @Override
    public List<ShelterStatusCategory> getShelterStatusCategoryList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

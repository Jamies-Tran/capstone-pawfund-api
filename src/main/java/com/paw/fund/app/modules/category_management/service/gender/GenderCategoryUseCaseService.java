package com.paw.fund.app.modules.category_management.service.gender;

import com.paw.fund.app.modules.category_management.domain.GenderCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.GenderSearch;
import com.paw.fund.app.modules.category_management.service.gender.usecase.IGenderCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenderCategoryUseCaseService implements IGenderCategoryUseCase {
    @NonNull
    GenderCategoryQueryService queryService;

    @Override
    public List<GenderCategory> getGenderList(GenderSearch search) {
        return queryService.findAll(search.value());
    }
}

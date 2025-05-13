package com.paw.fund.app.modules.category_management.service.map;

import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.repository.feign.data.place.Prediction;
import com.paw.fund.app.modules.category_management.service.map.uscase.IMapUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MapUseCaseService implements IMapUseCase {
    @NonNull
    MapCategoryQueryService queryService;

    @Override
    public Prediction predictPlaceList(CategorySearch categorySearch) {
        return queryService.autoComplete(categorySearch.value(), categorySearch.mapLimitRecord());
    }
}

package com.paw.fund.app.modules.category_management.service.map.uscase;

import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.repository.feign.data.place.Prediction;

public interface IMapUseCase {
    Prediction predictPlaceList(CategorySearch categorySearch);
}

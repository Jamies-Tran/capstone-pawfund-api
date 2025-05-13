package com.paw.fund.app.modules.category_management.service.map;

import com.paw.fund.app.modules.category_management.repository.feign.IMapPlaceHolder;
import com.paw.fund.app.modules.category_management.repository.feign.data.place.Prediction;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MapCategoryQueryService {
    @NonNull
    IMapPlaceHolder placeHolder;

    @NonFinal
    @Value("${app.goong.key}")
    String googKey;

    public Prediction autoComplete(String search, Integer limit) {
        return placeHolder.autoComplete(googKey, search, limit);
    }
}

package com.paw.fund.app.modules.map_management.service;

import com.paw.fund.app.modules.map_management.repository.feign.IMapServicePlaceHolder;
import com.paw.fund.app.modules.map_management.repository.feign.data.PlaceDetail;
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
public class MapQueryService {
    @NonNull
    IMapServicePlaceHolder placeHolder;

    @NonFinal
    @Value("${app.goong.key}")
    String apiKey;

    public PlaceDetail getPlaceDetailByPlaceId(String placeId) {
        return placeHolder.getPlaceDetailByPlaceId(apiKey, placeId);
    }
}

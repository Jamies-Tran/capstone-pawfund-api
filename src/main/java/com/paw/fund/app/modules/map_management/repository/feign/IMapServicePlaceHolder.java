package com.paw.fund.app.modules.map_management.repository.feign;

import com.paw.fund.app.modules.map_management.repository.feign.data.PlaceDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "map-service", url = "${app.goong.domain}")
public interface IMapServicePlaceHolder {
    @GetMapping("${app.goong.geocode}")
    PlaceDetail getPlaceDetailByPlaceId(
            @RequestParam(value = "api_key") String apiKey,
            @RequestParam(value = "place_id") String placeId
    );
}

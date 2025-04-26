package com.paw.fund.app.modules.category_management.repository.feign;

import com.paw.fund.app.modules.category_management.repository.feign.data.place.Prediction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auto-complete", url = "${app.goong.domain}")
public interface IMapPlaceHolder {
    @GetMapping("${app.goong.autocomplete}")
    Prediction autoComplete(
            @RequestParam(value = "api_key") String apiKey,
            @RequestParam(value = "input") String search,
            @RequestParam(required = false, value = "limit", defaultValue = "20") Integer limit
    );
}

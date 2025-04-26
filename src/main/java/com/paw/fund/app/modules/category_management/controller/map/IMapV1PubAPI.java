package com.paw.fund.app.modules.category_management.controller.map;

import com.paw.fund.app.modules.category_management.controller.map.models.PredictionsResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/map")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IMapV1PubAPI {
    @GetMapping("/auto-complete")
    ListResponse<PredictionsResponse> predictPlaceList(
            @RequestParam(required = false, value = "search", defaultValue = "") String search,
            @RequestParam(required = false, value = "limit", defaultValue = "20") Integer limit
    );
}

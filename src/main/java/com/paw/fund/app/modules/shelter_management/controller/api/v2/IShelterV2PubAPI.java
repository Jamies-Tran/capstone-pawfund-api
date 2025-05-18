package com.paw.fund.app.modules.shelter_management.controller.api.v2;

import com.paw.fund.app.modules.shelter_management.controller.api.models.ShelterResponse;
import com.paw.fund.utils.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v2/public/shelter")
@Tag(name = "Shelter V2", description = "QL trung tâm cứu trợ")
public interface IShelterV2PubAPI {
    @GetMapping
    @Operation(
            summary = "DS trung tâm cứu trợ đang hoạt động",
            description = """
                    - Người dùng tìm kiếm DS trung tâm cứu trợ đang hoạt động
                    - [USER - Người dùng (Đã xác thực | Chưa xác thực)]
                    """)
    PageResponse<ShelterResponse> getShelterList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,

            @RequestParam(required = false, value = "latitude", defaultValue = "0.0")
            BigDecimal latitude,

            @RequestParam(required = false, value = "longitude", defaultValue = "0.0")
            BigDecimal longitude,

            @RequestParam(required = false, value = "radius", defaultValue = "")
            BigDecimal radius,

            @RequestParam(required = false, value = "timeRange", defaultValue = "")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> timeRange,

            @RequestParam(required = false, value = "sorter", defaultValue = "updatedAt")
            String sorter,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );
}

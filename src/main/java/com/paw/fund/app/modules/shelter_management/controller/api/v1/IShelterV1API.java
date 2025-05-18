package com.paw.fund.app.modules.shelter_management.controller.api.v1;

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

@RequestMapping("/v1/api/shelter")
@Tag(name = "Shelter V1", description = "QL trung tâm cứu trợ")
public interface IShelterV1API {
    @GetMapping
    @Operation(
            summary = "DS trung tâm cứu trợ",
            description = """
                    - Người dùng đã xác thực tìm kiếm DS trung tâm cứu trợ
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    """)
    PageResponse<ShelterResponse> getShelterList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,

            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,

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

    @GetMapping("/distance")
    @Operation(
            summary = "DS khoản cách trung tâm cứu trợ",
            description = """
                    - Người dùng đã xác thực tìm kiếm DS khoản cách trung tâm cứu trợ
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    """)
    PageResponse<ShelterResponse> getShelterDistanceList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,

            @RequestParam(required = false, value = "latitude", defaultValue = "0.0")
            BigDecimal latitude,

            @RequestParam(required = false, value = "longitude", defaultValue = "0.0")
            BigDecimal longitude,

            @RequestParam(required = false, value = "radius", defaultValue = "")
            BigDecimal radius,

            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,

            @RequestParam(required = false, value = "timeRange", defaultValue = "")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> timeRange,

            @RequestParam(required = false, value = "sorter", defaultValue = "updated_at")
            String sorter,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );
}

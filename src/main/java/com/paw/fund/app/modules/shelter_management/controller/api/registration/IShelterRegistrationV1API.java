package com.paw.fund.app.modules.shelter_management.controller.api.registration;

import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterRegistrationRequest;
import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterRegistrationResponse;
import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterResponse;
import com.paw.fund.utils.response.PageResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/api/shelter/register")
@Tag(name = "Shelter Registration V1", description = "QL đăng ký trung tâm cứu trợ")
public interface IShelterRegistrationV1API {
    @PostMapping
    @Operation(
            summary = "Tạo đăng ký trung tâm cứu trợ",
            description = """
                    - Người dùng đã xác thực tạo đăng ký trung tâm cứu trợ
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    """)
    ValueResponse<ShelterResponse> registerShelter(
            @RequestBody @Valid
            ShelterRegistrationRequest request);


    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Xem danh sách các yêu cầu đăng ký trung tâm cứu trợ đang xử lý",
            description = """
                    - Admin xem danh sách các yêu cầu đăng ký trung tâm cứu trợ đang xử lý
                    - [ADMIN - Quản trị viên]
                    """)
    PageResponse<ShelterRegistrationResponse> getShelterRegistrationProcessingByAccount(
            @RequestParam(required = false, value = "requestAtTimeRange", defaultValue = "")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> requestAtTimeRange,

            @RequestParam(required = false, value = "receivedAtTimeRange", defaultValue = "")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> receivedAtTimeRange,

            @RequestParam(required = false, value = "approvedAtTimeRange", defaultValue = "")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> approvedAtTimeRange,

            @RequestParam(required = false, value = "rejectedAtTimeRange", defaultValue = "")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> rejectedAtTimeRange,

            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );
}

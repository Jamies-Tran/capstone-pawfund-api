package com.paw.fund.app.modules.pet_management.controller.health.record.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.health.record.models.PetHealthRecordResponse;
import com.paw.fund.utils.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/public/pet/health-record")
@Tag(name = "Pet Health Record V1", description = "QL hồ sơ sức khỏe thú cưng")
public interface IPetHealthRecordV1PubAPI {
    @GetMapping("/{petId}/list")
    @Operation(
            summary = "Xem danh sách hồ sơ sức khỏe của một thú cưng",
            description = """
                    - Xem danh sách hồ sơ sức khỏe của một thú cưng
                    - [USER - Người dùng]
                    """)
    PageResponse<PetHealthRecordResponse> getPetHealthRecordList(
            @PathVariable
            Long petId,

            @RequestParam(required = false, value = "diagnosisSearch", defaultValue = "")
            String diagnosisSearch,

            @RequestParam(required = false, value = "treatmentSearch", defaultValue = "")
            String treatmentSearch,

            @RequestParam(required = false, value = "timeRange", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> timeRange,

            @RequestParam(required = false, value = "checkupDateTimeRange", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> checkupDateTimeRange,

            @RequestParam(required = false, value = "healthStatusCodes", defaultValue = "")
            List<String> healthStatusCodes,

            @RequestParam(required = false, value = "sort", defaultValue = "updatedAt")
            String sort,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );
}

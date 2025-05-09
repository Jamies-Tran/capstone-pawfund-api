package com.paw.fund.app.modules.log_management.controller.pet.v1;

import com.paw.fund.app.modules.log_management.controller.pet.models.PetActivityLogResponse;
import com.paw.fund.utils.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/public/pet-activity-log")
@Tag(name = "Log V1", description = "QL nhật ký hoạt động")
public interface IPetActivityLogV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Xem danh sách nhật ký hoạt động của pet",
            description = """
                    - Xem danh sách nhật ký hoạt động của pet
                    - [USER - Người dùng]
                    """)
    PageResponse<PetActivityLogResponse> getPetActivityLogList(
            @RequestParam(required = false, value = "accountSearch", defaultValue = "")
            String accountSearch,

            @RequestParam(required = false, value = "descriptionSearch", defaultValue = "")
            String descriptionSearch,

            @RequestParam(required = false, value = "timeRange", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            List<LocalDateTime> timeRange,

            @RequestParam(required = false, value = "actionCodes", defaultValue = "")
            List<String> actionCodes,

            @RequestParam(required = false, value = "sorter", defaultValue = "updatedAt")
            String sorter,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );
}

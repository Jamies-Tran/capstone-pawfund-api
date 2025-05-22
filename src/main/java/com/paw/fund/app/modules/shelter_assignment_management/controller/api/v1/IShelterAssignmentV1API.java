package com.paw.fund.app.modules.shelter_assignment_management.controller.api.v1;

import com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.ShelterAssignmentListRequest;
import com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.ShelterAssignmentResponse;
import com.paw.fund.utils.response.ListResponse;
import com.paw.fund.utils.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/api/shelter-assignment")
@Tag(name = "Shelter Assignment V1", description = "QL phân công trung tâm cứu trợ")
public interface IShelterAssignmentV1API {
    @PostMapping("/{petIntakeRegistrationId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Tạo danh sách phân công trung tâm cứu trợ",
            description = """
                    - Tạo danh sách phân công trung tâm cứu trợ
                    - [Admin - Quản trị viên]
                    """)
    ListResponse<ShelterAssignmentResponse> createShelterAssignmentList(
            @PathVariable
            Long petIntakeRegistrationId,

            @Valid @RequestBody
            ShelterAssignmentListRequest request);

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Xem danh sách phân công trung tâm cứu trợ",
            description = """
                    - Xem danh sách phân công trung tâm cứu trợ
                    - [Admin - Quản trị viên]
                    """)
    PageResponse<ShelterAssignmentResponse> getShelterAssignmentList(
            @RequestParam(required = false, value = "shelterId", defaultValue = "")
            Long shelterId,

            @RequestParam(required = false, value = "petIntakeRegistrationId", defaultValue = "")
            Long petIntakeRegistrationId,

            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(required = false, value = "timeRange", defaultValue = "")
            List<LocalDateTime> timeRange,

            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,

            @RequestParam(required = false, value = "sorter", defaultValue = "updatedAt")
            String sorter,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );


}

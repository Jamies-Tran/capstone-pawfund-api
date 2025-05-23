package com.paw.fund.app.modules.shelter_assignment_management.controller.api.v1;

import com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.ShelterAssignmentRequest;
import com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.ShelterAssignmentResponse;
import com.paw.fund.app.modules.shelter_assignment_management.controller.api.models.reason.ShelterAssignmentCancelReason;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/shelter-assignment/{shelterAssignmentId}")
@Tag(name = "Shelter Assignment V1", description = "QL phân công trung tâm cứu trợ")
public interface IShelterAssignmentPathV1API {
    @GetMapping
    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_SHELTER_OWNER'})")
    @Operation(
            summary = "Xem chi tiết phân công trung tâm cứu trợ",
            description = """
                    - Xem chi tiết phân công trung tâm cứu trợ
                    - [Admin|Shelter Owner - Quản trị viên|Chủ trung tâm cứu trợ]
                    """)
    ValueResponse<ShelterAssignmentResponse> getShelterAssignmentDetail(@PathVariable Long shelterAssignmentId);

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Cập nhật phân công trung tâm cứu trợ",
            description = """
                    - Cập nhật phân công trung tâm cứu trợ
                    - [Admin - Quản trị viên]
                    """)
    ValueResponse<ShelterAssignmentResponse> updateShelterAssignment(
            @PathVariable
            Long shelterAssignmentId,

            @Valid @RequestBody
            ShelterAssignmentRequest request);

    @PutMapping("/receive")
    @PreAuthorize("hasAnyRole({'ROLE_STAFF', 'ROLE_SHELTER_OWNER'})")
    @Operation(
            summary = "Tiếp nhận phân công trung tâm cứu trợ",
            description = """
                    - Tiếp nhận phân công trung tâm cứu trợ
                    - [Staff|Shop Owner - Nhân viên | Chủ trung tâm cứu trợ]
                    """)
    ValueResponse<ShelterAssignmentResponse> receiveShelterAssignment(@PathVariable Long shelterAssignmentId);

    @PutMapping("/cancel")
    @PreAuthorize("hasAnyRole({'ROLE_STAFF', 'ROLE_SHELTER_OWNER'})")
    @Operation(
            summary = "Hủy bỏ phân công trung tâm cứu trợ",
            description = """
                    - Hủy bỏ phân công trung tâm cứu trợ
                    - [Staff|Shop Owner - Nhân viên | Chủ trung tâm cứu trợ]
                    """)
    ValueResponse<ShelterAssignmentResponse> cancelShelterAssignment(
            @PathVariable
            Long shelterAssignmentId,

            @Valid @RequestBody
            ShelterAssignmentCancelReason rejectReason
    );

    @PutMapping("/complete")
    @PreAuthorize("hasAnyRole({'ROLE_STAFF', 'ROLE_SHELTER_OWNER'})")
    @Operation(
            summary = "Hoàn thành phân công trung tâm cứu trợ",
            description = """
                    - Hoàn thành phân công trung tâm cứu trợ
                    - [Staff|Shop Owner - Nhân viên | Chủ trung tâm cứu trợ]
                    """)
    ValueResponse<ShelterAssignmentResponse> completeShelterAssignment(@PathVariable Long shelterAssignmentId);
}

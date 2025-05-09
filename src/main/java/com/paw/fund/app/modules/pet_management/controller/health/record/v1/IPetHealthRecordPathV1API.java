package com.paw.fund.app.modules.pet_management.controller.health.record.v1;

import com.paw.fund.app.modules.pet_management.controller.health.record.models.PetHealthRecordResponse;
import com.paw.fund.app.modules.pet_management.controller.health.record.models.PetHealthRecordUpdateRequest;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/pet/health-record/{petHealthRecordId}")
@Tag(name = "Pet Health Record V1", description = "QL hồ sơ sức khỏe thú cưng")
public interface IPetHealthRecordPathV1API {
    @PutMapping
    @PreAuthorize("hasRole('ROLE_STAFF')")
    @Operation(
            summary = "Cập nhật hồ sơ sức khỏe của một thú cưng",
            description = """
                    - Cập nhật hồ sơ sức khỏe của một thú cưng
                    - [STAFF - Nhân viên trung tâm cứu trợ]
                    """)
    ValueResponse<PetHealthRecordResponse> updatePetHealthRecord(
            @PathVariable
            Long petHealthRecordId,

            @Valid @RequestBody
            PetHealthRecordUpdateRequest request);
}

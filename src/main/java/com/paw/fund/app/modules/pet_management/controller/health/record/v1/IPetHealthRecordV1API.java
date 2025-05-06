package com.paw.fund.app.modules.pet_management.controller.health.record.v1;

import com.paw.fund.app.modules.pet_management.controller.health.record.models.PetHealthRecordRequest;
import com.paw.fund.app.modules.pet_management.controller.health.record.models.PetHealthRecordResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/pet/health-record")
@Tag(name = "Pet Health Record V1", description = "QL hồ sơ sức khỏe thú cưng")
public interface IPetHealthRecordV1API {
    @PostMapping
    @PreAuthorize("hasRole('ROLE_STAFF')")
    ValueResponse<PetHealthRecordResponse> createPetHealthRecord(
            @Valid @RequestBody
            PetHealthRecordRequest request);
}

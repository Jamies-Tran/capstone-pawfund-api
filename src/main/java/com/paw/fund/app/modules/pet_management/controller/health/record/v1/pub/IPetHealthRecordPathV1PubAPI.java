package com.paw.fund.app.modules.pet_management.controller.health.record.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.health.record.models.PetHealthRecordResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/public/pet/health-record/{petHealthRecordId}")
@Tag(name = "Pet Health Record V1", description = "QL hồ sơ sức khỏe thú cưng")
public interface IPetHealthRecordPathV1PubAPI {
    @GetMapping
    ValueResponse<PetHealthRecordResponse> getPetHealthRecordDetail(@PathVariable Long petHealthRecordId);
}

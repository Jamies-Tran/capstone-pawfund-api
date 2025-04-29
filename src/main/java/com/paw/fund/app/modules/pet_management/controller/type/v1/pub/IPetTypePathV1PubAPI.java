package com.paw.fund.app.modules.pet_management.controller.type.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/public/pet/type/{petTypeId}")
@Tag(name = "Pet Type V1", description = "QL loại thú cưng")
public interface IPetTypePathV1PubAPI {
    @GetMapping
    ValueResponse<PetTypeResponse> getPetTypeDetail(@PathVariable Long petTypeId);
}

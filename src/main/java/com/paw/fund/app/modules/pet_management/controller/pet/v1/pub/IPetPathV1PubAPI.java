package com.paw.fund.app.modules.pet_management.controller.pet.v1.pub;

import com.paw.fund.app.modules.pet_management.controller.pet.models.PetResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/public/pet/{petId}")
@Tag(name = "PET V1", description = "QL thú cưng")
public interface IPetPathV1PubAPI {
    @GetMapping
    ValueResponse<PetResponse> getPetDetail(@PathVariable Long petId);
}

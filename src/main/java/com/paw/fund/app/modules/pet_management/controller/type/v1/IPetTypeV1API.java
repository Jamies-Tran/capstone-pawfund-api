package com.paw.fund.app.modules.pet_management.controller.type.v1;

import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeListRequest;
import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeRequest;
import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeResponse;
import com.paw.fund.utils.response.ListResponse;
import com.paw.fund.utils.response.PageResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/v1/api/pet/type")
@Tag(name = "Pet Type V1", description = "QL loại thú cưng")
public interface IPetTypeV1API {
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ValueResponse<PetTypeResponse> createPetType(
            @Valid @RequestBody
            PetTypeRequest request);

    @PostMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ListResponse<PetTypeResponse> createPetTypeList(
            @Valid @RequestBody
            PetTypeListRequest request);

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    PageResponse<PetTypeResponse> getPetTypeList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,

            @RequestParam(required = false, value = "statusCodes", defaultValue = "")
            List<String> statusCodes,

            @RequestParam(required = false, value = "current", defaultValue = "0")
            Integer current,

            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
            Integer pageSize
    );
}

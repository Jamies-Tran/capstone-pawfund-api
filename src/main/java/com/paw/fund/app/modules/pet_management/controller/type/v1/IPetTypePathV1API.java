package com.paw.fund.app.modules.pet_management.controller.type.v1;

import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeRequest;
import com.paw.fund.app.modules.pet_management.controller.type.models.PetTypeResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/pet/type/{petTypeId}")
@Tag(name = "Pet Type V1", description = "QL loại thú cưng")
public interface IPetTypePathV1API {
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Xem chi tiết loại thú cưng",
            description = """
                    - Xem chi tiết loại thú cưng
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<PetTypeResponse> getPetTypeDetail(@PathVariable Long petTypeId);


    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Cập nhật loại thú cưng",
            description = """
                    - Cập nhật loại thú cưng
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<PetTypeResponse> updatePetType(
            @PathVariable
            Long petTypeId,

            @Valid @RequestBody
            PetTypeRequest request);

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Xóa loại thú cưng",
            description = """
                    - Xóa loại thú cưng
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<?> deletePetType(@PathVariable Long petTypeId);

    @PatchMapping("/active")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Kích hoạt loại thú cưng",
            description = """
                    - Kích hoạt loại thú cưng
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<PetTypeResponse> activePetType(@PathVariable Long petTypeId);

    @PatchMapping("/block")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Khóa loại thú cưng",
            description = """
                    - Khóa loại thú cưng
                    - [ADMIN - Quản trị viên]
                    """)
    ValueResponse<PetTypeResponse> blockPetType(@PathVariable Long petTypeId);
}

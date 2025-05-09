package com.paw.fund.app.modules.pet_management.controller.pet.v1;

import com.paw.fund.app.modules.pet_management.controller.pet.models.PetRequest;
import com.paw.fund.app.modules.pet_management.controller.pet.models.PetResponse;
import com.paw.fund.app.modules.pet_management.controller.pet.models.PetUpdateRequest;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/pet/{petId}")
@Tag(name = "Pet V1", description = "QL thú cưng")
public interface IPetPathV1API {
    @PutMapping
    @PreAuthorize("hasAnyRole({'ROLE_SHELTER_OWNER', 'ROLE_STAFF'})")
    @Operation(
            summary = "Cập nhật thú cưng",
            description = """
                    - Cập nhật thú cưng
                    - [SHELTER OWNER | STAFF - Chủ trung tâm cứu trợ | Nhân viên trung tâm cứu trợ]
                    """)
    ValueResponse<PetResponse> updatePet(
            @PathVariable
            Long petId,

            @Valid @RequestBody
            PetUpdateRequest request);

    @DeleteMapping
    @PreAuthorize("hasAnyRole({'ROLE_SHELTER_OWNER', 'ROLE_STAFF'})")
    @Operation(
            summary = "Xóa thú cưng",
            description = """
                    - Xóa thú cưng
                    - [SHELTER OWNER | STAFF - Chủ trung tâm cứu trợ | Nhân viên trung tâm cứu trợ]
                    """)
    ValueResponse<?> deletePet(@PathVariable Long petId);

    @PatchMapping("/set-adoptable")
    @PreAuthorize("hasAnyRole({'ROLE_SHELTER_OWNER', 'ROLE_STAFF'})")
    @Operation(
            summary = "Đổi trạng thái có thể nhận nuôi cho thú cưng",
            description = """
                    - Đổi trạng thái có thể nhận nuôi cho thú cưng
                    - [SHELTER OWNER | STAFF - Chủ trung tâm cứu trợ | Nhân viên trung tâm cứu trợ]
                    """)
    ValueResponse<PetResponse> setAdoptablePet(@PathVariable Long petId);

    @PatchMapping("/set-not-adoptable")
    @PreAuthorize("hasAnyRole({'ROLE_SHELTER_OWNER', 'ROLE_STAFF'})")
    @Operation(
            summary = "Đổi trạng thái không thể nhận nuôi cho thú cưng",
            description = """
                    - Đổi trạng thái không thể nhận nuôi cho thú cưng
                    - [SHELTER OWNER | STAFF - Chủ trung tâm cứu trợ | Nhân viên trung tâm cứu trợ]
                    """)
    ValueResponse<PetResponse> setNotAdoptablePet(@PathVariable Long petId);
}

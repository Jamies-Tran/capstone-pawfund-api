package com.paw.fund.app.modules.shelter_management.controller.api.registration;

import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterRegistrationRejectRequest;
import com.paw.fund.app.modules.shelter_management.controller.api.registration.models.ShelterRegistrationResponse;
import com.paw.fund.utils.response.ValueResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/shelter/register/{shelterRegisterId}")
public interface IShelterRegisterPathV1API {
    @PatchMapping("/receive")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ValueResponse<ShelterRegistrationResponse> receiveShelter(@PathVariable Long shelterRegisterId);

    @PatchMapping("/approve")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ValueResponse<ShelterRegistrationResponse> approveShelter(@PathVariable Long shelterRegisterId);

    @PatchMapping("/reject")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ValueResponse<ShelterRegistrationResponse> rejectShelter(@PathVariable Long shelterRegisterId,
                                                             @RequestBody @Valid ShelterRegistrationRejectRequest request);
}

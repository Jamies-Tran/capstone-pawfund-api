package com.paw.fund.app.modules.pet_intake_registration_management.domain.usecase;

import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;

@Builder
public record PetIntakeRegistrationFilter(
        PetIntakeRegistrationSearchCriteria searchCriteria,
        PageRequestCustom pageRequestCustom
) {
    public static PetIntakeRegistrationFilter of(PetIntakeRegistrationSearchCriteria searchCriteria,
                                                 PageRequestCustom pageRequestCustom) {
        return PetIntakeRegistrationFilter.builder()
                .searchCriteria(searchCriteria)
                .pageRequestCustom(pageRequestCustom)
                .build();
    }
}

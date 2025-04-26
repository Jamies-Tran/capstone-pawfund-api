package com.paw.fund.app.modules.shelter_management.domain.usecase.registration;

import com.paw.fund.enums.EShelterRegistrationStatus;
import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;

import java.util.List;

@Builder
public record ShelterRegistrationFilter(
        ShelterRegistrationSearchCriteria searchCriteria,
        PageRequestCustom pageRequestCustom
) {
    public static ShelterRegistrationFilter of(
            ShelterRegistrationSearchCriteria searchCriteria,
            PageRequestCustom pageRequestCustom) {
        return ShelterRegistrationFilter.builder()
                .searchCriteria(searchCriteria)
                .pageRequestCustom(pageRequestCustom)
                .build();
    }

    public static ShelterRegistrationFilter prepareForAdmin() {
        ShelterRegistrationSearchCriteria searchCriteria = ShelterRegistrationSearchCriteria.builder()
                .statusCodes(List.of(EShelterRegistrationStatus.NEW.getCode()))
                .build();
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(0, 20, "requestAt");

        return ShelterRegistrationFilter.of(searchCriteria, pageRequestCustom);
    }
}

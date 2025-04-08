package com.paw.fund.app.modules.license_management.controller.models.section;

import lombok.Builder;

import java.util.List;

@Builder
public record LicenseSectionResponse(
        Long licenseSectionId,
        Long licenseId,
        String sectionTitle,
        List<SectionContentResponse> sectionContent,
        Boolean isRequired
) {
}

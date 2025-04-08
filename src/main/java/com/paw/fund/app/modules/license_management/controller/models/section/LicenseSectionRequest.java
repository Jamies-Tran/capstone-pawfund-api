package com.paw.fund.app.modules.license_management.controller.models.section;

import lombok.Builder;

import java.util.List;

@Builder
public record LicenseSectionRequest(
        String sectionTitle,
        List<SectionContentRequest> sectionContent,
        Boolean isRequired
) {
}

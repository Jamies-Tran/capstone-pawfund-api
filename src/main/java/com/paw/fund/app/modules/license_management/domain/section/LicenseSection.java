package com.paw.fund.app.modules.license_management.domain.section;

import lombok.Builder;
import lombok.With;

import java.util.List;
import java.util.Objects;

@Builder
public record LicenseSection(
        Long licenseSectionId,
        @With Long licenseId,
        String sectionTitle,
        List<SectionContent> sectionContent,
        Boolean isRequired
) {
}

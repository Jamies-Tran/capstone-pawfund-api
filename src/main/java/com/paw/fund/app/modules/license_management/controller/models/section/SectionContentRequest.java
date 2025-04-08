package com.paw.fund.app.modules.license_management.controller.models.section;

import lombok.Builder;

@Builder
public record SectionContentRequest(
        String content
) {
}

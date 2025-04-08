package com.paw.fund.app.modules.license_management.controller.models.section;

import com.paw.fund.app.modules.license_management.domain.section.SectionContent;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ILicenseSectionModelMapper {
    SectionContent toDto(SectionContentRequest request);

    SectionContentResponse toResponse(SectionContent dto);
}

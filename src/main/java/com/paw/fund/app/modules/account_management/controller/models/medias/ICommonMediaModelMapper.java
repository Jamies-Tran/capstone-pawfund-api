package com.paw.fund.app.modules.account_management.controller.models.medias;

import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICommonMediaModelMapper {
    @Mapping(target = "mediaType.code", source = "dto.mediaTypeCode")
    @Mapping(target = "mediaType.name", source = "dto.mediaTypeName")
    CommonMediaResponse toResponse(CommonMedia dto);
}

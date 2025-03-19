package com.paw.fund.app.modules.media_management.domain.common;

import com.paw.fund.app.modules.media_management.repository.database.common.CommonMediaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ICommonMediaMapper {
    CommonMedia toDto(CommonMediaEntity entity);

    CommonMediaEntity toEntity(CommonMedia dto);
}

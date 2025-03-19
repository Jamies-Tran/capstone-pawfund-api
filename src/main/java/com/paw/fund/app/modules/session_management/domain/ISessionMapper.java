package com.paw.fund.app.modules.session_management.domain;

import com.paw.fund.app.modules.session_management.repository.database.SessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ISessionMapper {
    Session toDto(SessionEntity entity);

    SessionEntity toEntity(Session dto);

    void update(@MappingTarget SessionEntity entity, Session dto);
}

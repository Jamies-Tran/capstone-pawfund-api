package com.paw.fund.app.modules.auditable_management.domain;

import com.paw.fund.app.modules.auditable_management.repository.database.AuditableEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IAuditableMapper {
    Auditable toDto(AuditableEntity entity);

    AuditableEntity toEntity(Auditable dto);
}

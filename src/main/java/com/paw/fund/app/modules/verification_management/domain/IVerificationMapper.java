package com.paw.fund.app.modules.verification_management.domain;

import com.paw.fund.app.modules.verification_management.repository.database.VerificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IVerificationMapper {
    VerificationEntity toEntity(Verification dto);

    Verification toDto(VerificationEntity entity);
}

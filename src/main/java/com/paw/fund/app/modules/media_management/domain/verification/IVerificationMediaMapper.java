package com.paw.fund.app.modules.media_management.domain.verification;

import com.paw.fund.app.modules.media_management.repository.database.verification.VerificationMediaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IVerificationMediaMapper {
    VerificationMedia toDto(VerificationMediaEntity entity);

    VerificationMediaEntity toEntity(VerificationMedia dto);

    void update(@MappingTarget VerificationMediaEntity entity, VerificationMedia dto);
}

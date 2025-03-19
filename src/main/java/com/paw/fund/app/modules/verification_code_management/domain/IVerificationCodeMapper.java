package com.paw.fund.app.modules.verification_code_management.domain;

import com.paw.fund.app.modules.verification_code_management.repository.database.VerificationCodeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IVerificationCodeMapper {
    VerificationCodeEntity toEntity(VerificationCode dto);

    VerificationCode toDto(VerificationCodeEntity entity);
}

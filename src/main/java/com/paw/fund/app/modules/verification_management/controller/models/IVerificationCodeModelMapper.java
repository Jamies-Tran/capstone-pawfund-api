package com.paw.fund.app.modules.verification_management.controller.models;

import com.paw.fund.app.modules.verification_management.domain.Verification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IVerificationCodeModelMapper {
    Verification toDto(VerificationEmailRequest request);

    VerificationCodeResponse toResponse(Verification dto);
}

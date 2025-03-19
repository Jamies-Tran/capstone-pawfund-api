package com.paw.fund.app.modules.verification_code_management.controller.models;

import com.paw.fund.app.modules.verification_code_management.domain.VerificationCode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IVerificationCodeModelMapper {
    VerificationCode toDto(VerificationCodeRequest request);

    VerificationCodeResponse toResponse(VerificationCode dto);
}

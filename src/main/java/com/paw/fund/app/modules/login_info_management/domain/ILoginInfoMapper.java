package com.paw.fund.app.modules.login_info_management.domain;

import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.Login;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.LoginInfo;
import com.paw.fund.app.modules.login_info_management.repository.database.LoginInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ILoginInfoMapper {
    Login toDto(LoginInfoEntity entity);

    LoginInfo toInfoDto(LoginInfoEntity entity);

    LoginInfoEntity toEntity(Login dto);

    void update(@MappingTarget LoginInfoEntity entity, Login dto);
}

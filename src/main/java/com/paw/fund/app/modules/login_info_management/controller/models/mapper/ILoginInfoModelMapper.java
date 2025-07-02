package com.paw.fund.app.modules.login_info_management.controller.models.mapper;

import com.paw.fund.app.modules.login_info_management.controller.models.LoginInfoResponse;
import com.paw.fund.app.modules.login_info_management.controller.models.LoginRequest;
import com.paw.fund.app.modules.login_info_management.controller.models.RefreshResponse;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.Login;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.LoginInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = IAccountModelMapper.class
)
public interface ILoginInfoModelMapper {
    LoginInfo toDto(LoginRequest request);


    @Mapping(target = "geometry.latitude", source = "latitude")
    @Mapping(target = "geometry.longitude", source = "longitude")
    LoginInfoResponse toResponse(Login dto);

    RefreshResponse toRefreshResponse(Login dto);
}

package com.paw.fund.app.modules.login_info_management.controller.models.mapper;

import com.paw.fund.app.modules.account_management.domain.role.Role;
import com.paw.fund.app.modules.login_info_management.controller.models.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        implementationName = "roleLoginInfoModelMapper"
)
public interface IRoleModelMapper {
    @Mapping(target = "role.code", source = "roleCode")
    @Mapping(target = "role.name", source = "roleName")
    RoleResponse toResponse(Role dto);
}

package com.paw.fund.app.modules.account_management.controller.models.role;

import com.paw.fund.app.modules.account_management.domain.role.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        implementationName = "roleModelMapper"
)
public interface IRoleModelMapper {
    @Mapping(target = "role.code", source = "roleCode")
    @Mapping(target = "role.name", source = "roleName")
    RoleResponse toModel(Role role);
}

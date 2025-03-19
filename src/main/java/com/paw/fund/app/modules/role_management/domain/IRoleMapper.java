package com.paw.fund.app.modules.role_management.domain;

import com.paw.fund.app.modules.role_management.repository.database.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleMapper {
    Role toDto(RoleEntity entity);
}

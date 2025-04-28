package com.paw.fund.app.modules.account_management.domain.role;

import com.paw.fund.app.modules.account_management.repository.database.role.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleMapper {
    Role toDto(RoleEntity entity);
}

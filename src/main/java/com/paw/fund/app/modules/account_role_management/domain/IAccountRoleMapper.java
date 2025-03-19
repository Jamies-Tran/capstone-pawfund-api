package com.paw.fund.app.modules.account_role_management.domain;

import com.paw.fund.app.modules.account_role_management.repository.database.AccountRoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountRoleMapper {
    AccountRole toDto(AccountRoleEntity entity);

    AccountRoleEntity toEntity(AccountRole dto);
}

package com.paw.fund.app.modules.account_management.domain.account.role;

import com.paw.fund.app.modules.account_management.repository.database.account.role.AccountRoleEntity;
import com.paw.fund.app.modules.account_management.repository.database.account.role.AccountRoleSummarizeInfoDAO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountRoleMapper {
    AccountRole toDto(AccountRoleEntity entity);

    AccountRoleEntity toEntity(AccountRole dto);

    AccountRoleSummarizeInfo toDto(AccountRoleSummarizeInfoDAO dao);
}

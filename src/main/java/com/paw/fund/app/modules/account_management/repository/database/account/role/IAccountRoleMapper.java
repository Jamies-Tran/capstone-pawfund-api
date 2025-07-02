package com.paw.fund.app.modules.account_management.repository.database.account.role;

import com.paw.fund.app.modules.account_management.domain.account.role.AccountRole;
import com.paw.fund.app.modules.account_management.domain.account.role.AccountRoleSummarizeInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountRoleMapper {
    AccountRole toDto(AccountRoleEntity entity);

    AccountRoleEntity toEntity(AccountRole dto);

    AccountRoleSummarizeInfo toDto(AccountRoleSummarizeInfoDAO dao);
}

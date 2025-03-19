package com.paw.fund.app.modules.log_management.domain.account;

import com.paw.fund.app.modules.log_management.repository.databse.account.AccountActivityLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IAccountActivityLogMapper {
    AccountActivityLog toDto(AccountActivityLogEntity entity);

    AccountActivityLogEntity toEntity(AccountActivityLog dto);
}

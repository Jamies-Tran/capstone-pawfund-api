package com.paw.fund.app.modules.login_info_management.controller.models.mapper;

import com.paw.fund.app.modules.login_info_management.controller.models.AccountResponse;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        implementationName = "AccountLoginModelMapper",
        uses = IRoleModelMapper.class
)
public interface IAccountModelMapper {
    @Mapping(target = "status.code", source = "statusCode")
    @Mapping(target = "status.name", source = "statusName")
    AccountResponse toResponse(Account account);
}

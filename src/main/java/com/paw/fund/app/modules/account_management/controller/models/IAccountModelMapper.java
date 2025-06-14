package com.paw.fund.app.modules.account_management.controller.models;

import com.paw.fund.app.modules.account_management.controller.models.role.IRoleModelMapper;
import com.paw.fund.app.modules.account_management.domain.account.Account;
import com.paw.fund.app.modules.account_management.domain.role.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {IRoleModelMapper.class}
)
public interface IAccountModelMapper {
    Account toDto(AccountRequest request);

    Account toDto(AccountRequest request, List<Role> roles);

    @Mapping(target = "gender.code", source = "genderCode")
    @Mapping(target = "gender.name", source = "genderName")
    @Mapping(target = "status.code", source = "statusCode")
    @Mapping(target = "status.name", source = "statusName")
    AccountResponse toResponse(Account dto);

    Account toDto(AccountUpdateRequest updateRequest);

    Account toDto(AccountV2Request request);

    Account toDto(AccountRoleRegisterRequest request);


}

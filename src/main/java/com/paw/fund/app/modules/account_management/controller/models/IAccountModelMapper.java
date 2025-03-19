package com.paw.fund.app.modules.account_management.controller.models;

import com.paw.fund.app.modules.account_management.domain.Account;
import com.paw.fund.app.modules.role_management.domain.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAccountModelMapper {
    Account toDto(AccountRequest request);

    Account toDto(AccountRequest request, List<Role> roles);

    AccountResponse toResponse(Account dto);

    Account toDto(AccountUpdateRequest updateRequest);
}

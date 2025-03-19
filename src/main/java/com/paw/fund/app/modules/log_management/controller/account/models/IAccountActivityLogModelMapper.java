package com.paw.fund.app.modules.log_management.controller.account.models;

import com.paw.fund.app.modules.log_management.domain.account.AccountActivityLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountActivityLogModelMapper {
    AccountActivityLogResponse toResponse(AccountActivityLog dto);
}

package com.paw.fund.app.modules.log_management.controller.pet.models;

import com.paw.fund.app.modules.log_management.domain.pet.PetActivityLog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPetActivityLogModelMapper {
    PetActivityLogResponse toResponse(PetActivityLog dto);
}

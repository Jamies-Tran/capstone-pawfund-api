package com.paw.fund.app.modules.pet_management.controller.hobby.models;

import com.paw.fund.app.modules.pet_management.domain.hobby.Hobby;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IHobbyModelMapper {
    Hobby toDto(HobbyRequest request);

    HobbyResponse toResponse(Hobby dto);
}

package com.paw.fund.app.modules.category_management.controller.map.models;

import com.paw.fund.app.modules.category_management.repository.feign.data.place.Prediction;
import com.paw.fund.app.modules.category_management.repository.feign.data.place.Predictions;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMapModelMapper {
    PredictionResponse toResponse(Prediction dto);
}

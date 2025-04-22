package com.paw.fund.app.modules.category_management.controller.map.models;

import lombok.Builder;

import java.util.List;

@Builder
public record PredictionResponse(List<PredictionsResponse> predictions) {
}

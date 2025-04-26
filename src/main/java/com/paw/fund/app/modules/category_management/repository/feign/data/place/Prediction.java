package com.paw.fund.app.modules.category_management.repository.feign.data.place;

import lombok.Builder;

import java.util.List;

@Builder
public record Prediction(
    List<Predictions> predictions
) {
}

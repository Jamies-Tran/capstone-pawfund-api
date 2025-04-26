package com.paw.fund.app.modules.category_management.repository.feign.data.place;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record Predictions(
        @JsonProperty("description")
        String address,

        @JsonProperty("place_id")
        String placeId
) {
}

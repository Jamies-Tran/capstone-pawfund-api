package com.paw.fund.app.modules.shelter_management.repository.feign.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record Geometry(
        @JsonProperty("lat")
        BigDecimal latitude,

        @JsonProperty("lng")
        BigDecimal longitude
) {
}

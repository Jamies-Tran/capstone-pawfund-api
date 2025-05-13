package com.paw.fund.app.modules.map_management.repository.feign.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlaceGeometry(
        @JsonProperty("location")
        Geometry geometry
) {
}

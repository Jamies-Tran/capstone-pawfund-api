package com.paw.fund.app.modules.map_management.repository.feign.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PlaceResult(
        @JsonProperty("address_components")
        List<PlaceComponent> placeComponents,

        @JsonProperty("formatted_address")
        String address,

        @JsonProperty("geometry")
        PlaceGeometry placeGeometry
) {
}

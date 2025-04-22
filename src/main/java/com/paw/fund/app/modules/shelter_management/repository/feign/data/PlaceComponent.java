package com.paw.fund.app.modules.shelter_management.repository.feign.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public record PlaceComponent(
        @JsonProperty("long_name")
        String placeName
) {
    public PlaceComponent {
        placeName = Optional.ofNullable(placeName)
                .map(String::strip)
                .orElse(null);
    }
}

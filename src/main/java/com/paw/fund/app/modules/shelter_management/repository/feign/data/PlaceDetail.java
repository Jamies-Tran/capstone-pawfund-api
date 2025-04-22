package com.paw.fund.app.modules.shelter_management.repository.feign.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PlaceDetail(
        List<PlaceResult> results
) {
}

package com.paw.fund.app.modules.map_management.repository.feign.data;

import java.util.List;

public record PlaceDetail(
        List<PlaceResult> results
) {
}

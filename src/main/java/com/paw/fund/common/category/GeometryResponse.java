package com.paw.fund.common.category;

import java.math.BigDecimal;

public record GeometryResponse(
        BigDecimal latitude,
        BigDecimal longitude
) {
}

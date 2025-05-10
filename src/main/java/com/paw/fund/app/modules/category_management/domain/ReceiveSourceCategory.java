package com.paw.fund.app.modules.category_management.domain;

import com.paw.fund.enums.EReceiveSource;
import lombok.Builder;

@Builder
public record ReceiveSourceCategory(
        String code,
        String name
) {
    public static ReceiveSourceCategory of(EReceiveSource receiveSource) {
        return ReceiveSourceCategory.builder()
                .code(receiveSource.getCode())
                .name(receiveSource.getName())
                .build();
    }
}

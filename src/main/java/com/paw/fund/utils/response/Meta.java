package com.paw.fund.utils.response;

import lombok.Builder;
import org.springframework.data.domain.Page;

@Builder
public record Meta(
        Integer current,
        Integer pageSize,
        Long total
) {
    public static Meta of(Page<?> page) {
        return Meta.builder()
                .current(page.getNumber())
                .pageSize(page.getSize())
                .total(page.getTotalElements())
                .build();
    }
}

package com.paw.fund.app.modules.form_management.domain.form.usecase;

import com.paw.fund.utils.request.PageRequestCustom;
import lombok.Builder;

@Builder
public record FormFilter(FormSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
    public static FormFilter of(FormSearchCriteria searchCriteria, PageRequestCustom pageRequestCustom) {
        return FormFilter.builder()
                .searchCriteria(searchCriteria)
                .pageRequestCustom(pageRequestCustom)
                .build();
    }
}

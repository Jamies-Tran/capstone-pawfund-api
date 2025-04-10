package com.paw.fund.app.modules.category_management.service.form.status;

import com.paw.fund.app.modules.category_management.domain.FormStatusCategory;
import com.paw.fund.enums.EFormStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormStatusCategoryQueryService {
    public List<FormStatusCategory> findAll(String search) {
        return Stream.of(EFormStatus.values())
                .map(FormStatusCategory::of)
                .filter(x -> !StringUtils.hasText(search)
                    || x.name().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }
}

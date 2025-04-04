package com.paw.fund.app.modules.category_management.service.form.type;

import com.paw.fund.app.modules.category_management.domain.FormTypeCategory;
import com.paw.fund.enums.EFormType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormTypeCategoryQueryService {
    public List<FormTypeCategory> findAll(String search) {
        return Stream.of(EFormType.values())
                .map(FormTypeCategory::of)
                .filter(x -> x.name().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }
}

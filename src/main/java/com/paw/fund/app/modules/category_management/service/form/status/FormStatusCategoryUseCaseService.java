package com.paw.fund.app.modules.category_management.service.form.status;

import com.paw.fund.app.modules.category_management.domain.FormStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.usecase.IFormStatusCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormStatusCategoryUseCaseService implements IFormStatusCategoryUseCase {
    @NonNull
    FormStatusCategoryQueryService queryService;

    @Override
    public List<FormStatusCategory> findFormStatusList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

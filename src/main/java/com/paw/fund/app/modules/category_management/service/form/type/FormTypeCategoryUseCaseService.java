package com.paw.fund.app.modules.category_management.service.form.type;

import com.paw.fund.app.modules.category_management.domain.FormTypeCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.usecase.IFormTypeCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormTypeCategoryUseCaseService implements IFormTypeCategoryUseCase {
    @NonNull
    FormTypeCategoryQueryService queryService;

    @Override
    public List<FormTypeCategory> getFormTypeList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

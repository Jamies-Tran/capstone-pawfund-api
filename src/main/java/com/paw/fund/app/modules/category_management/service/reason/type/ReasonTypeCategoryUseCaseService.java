package com.paw.fund.app.modules.category_management.service.reason.type;

import com.paw.fund.app.modules.category_management.domain.ReasonTypeCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.reason.type.usecase.IReasonTypeCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReasonTypeCategoryUseCaseService implements IReasonTypeCategoryUseCase {
    @NonNull
    ReasonTypeCategoryQueryService queryService;

    @Override
    public List<ReasonTypeCategory> getReasonTypeCategoryList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

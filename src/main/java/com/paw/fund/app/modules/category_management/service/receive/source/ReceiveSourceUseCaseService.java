package com.paw.fund.app.modules.category_management.service.receive.source;

import com.paw.fund.app.modules.category_management.domain.ReceiveSourceCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.receive.source.usecase.IReceiveSourceUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReceiveSourceUseCaseService implements IReceiveSourceUseCase {
    @NonNull
    ReceiveSourceQueryService queryService;

    @Override
    public List<ReceiveSourceCategory> getReceiveSourceCategoryList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

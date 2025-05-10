package com.paw.fund.app.modules.category_management.service.receive.source.usecase;

import com.paw.fund.app.modules.category_management.domain.ReceiveSourceCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;

import java.util.List;

public interface IReceiveSourceUseCase {
    List<ReceiveSourceCategory> getReceiveSourceCategoryList(CategorySearch search);
}

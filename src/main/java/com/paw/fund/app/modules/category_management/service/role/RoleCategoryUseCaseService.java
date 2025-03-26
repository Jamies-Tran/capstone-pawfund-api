package com.paw.fund.app.modules.category_management.service.role;

import com.paw.fund.app.modules.category_management.domain.RoleCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.RoleSearch;
import com.paw.fund.app.modules.category_management.service.role.usecase.IRoleCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleCategoryUseCaseService implements IRoleCategoryUseCase {
    @NonNull
    RoleCategoryQueryService queryService;

    @Override
    public List<RoleCategory> getRoleList(RoleSearch search) {
        return queryService.findAll(search.value());
    }
}

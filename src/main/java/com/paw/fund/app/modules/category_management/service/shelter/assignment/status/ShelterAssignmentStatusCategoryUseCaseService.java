package com.paw.fund.app.modules.category_management.service.shelter.assignment.status;

import com.paw.fund.app.modules.category_management.domain.ShelterAssignmentStatusCategory;
import com.paw.fund.app.modules.category_management.domain.usecase.CategorySearch;
import com.paw.fund.app.modules.category_management.service.shelter.assignment.status.usecase.IShelterAssignmentStatusCategoryUseCase;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShelterAssignmentStatusCategoryUseCaseService implements IShelterAssignmentStatusCategoryUseCase {
    @NonNull
    ShelterAssignmentStatusCategoryQueryService queryService;

    @Override
    public List<ShelterAssignmentStatusCategory> getShelterAssignmentStatusCategoryList(CategorySearch search) {
        return queryService.findAll(search.value());
    }
}

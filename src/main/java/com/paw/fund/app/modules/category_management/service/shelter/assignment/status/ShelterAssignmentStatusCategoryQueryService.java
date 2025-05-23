package com.paw.fund.app.modules.category_management.service.shelter.assignment.status;

import com.paw.fund.app.modules.category_management.domain.ShelterAssignmentStatusCategory;
import com.paw.fund.enums.EShelterAssignmentStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ShelterAssignmentStatusCategoryQueryService {
    public List<ShelterAssignmentStatusCategory> findAll(String search) {
        return Stream.of(EShelterAssignmentStatus.values())
                .map(ShelterAssignmentStatusCategory::of)
                .filter(x -> {
                    if(StringUtils.hasText(search)) {
                        return x.name().toLowerCase().contains(search.toLowerCase());
                    }

                    return true;
                })
                .toList();
    }
}

package com.paw.fund.app.modules.category_management.service.pet.health.status;

import com.paw.fund.app.modules.category_management.domain.PetHealthStatusCategory;
import com.paw.fund.enums.EHealthStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class PetHealthStatusCategoryQueryService {
    public List<PetHealthStatusCategory> findAll(String search) {
        return Stream.of(EHealthStatus.values())
                .map(PetHealthStatusCategory::of)
                .filter(x -> !Objects.equals(x, EHealthStatus.NONE))
                .filter(x -> {
                    if(StringUtils.hasText(search)) {
                        return x.name().toLowerCase().contains(search.toLowerCase());
                    }

                    return true;
                })
                .toList();
    }
}

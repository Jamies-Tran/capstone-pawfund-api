package com.paw.fund.app.modules.category_management.service.pet.status;

import com.paw.fund.app.modules.category_management.domain.PetStatusCategory;
import com.paw.fund.enums.EPetStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

@Service
public class PetStatusCategoryQueryService {
    public List<PetStatusCategory> findAll(String search) {
        return Stream.of(EPetStatus.values())
                .map(PetStatusCategory::of)
                .filter(x -> {
                    if(StringUtils.hasText(search)) {
                        return x.name().toLowerCase().contains(search.toLowerCase());
                    }

                    return true;
                })
                .toList();
    }
}

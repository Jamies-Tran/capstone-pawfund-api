package com.paw.fund.app.modules.category_management.service.pet.color;

import com.paw.fund.app.modules.category_management.domain.PetColorCategory;
import com.paw.fund.enums.EColor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

@Service
public class PetColorCategoryQueryService {
    public List<PetColorCategory> findAll(String search) {
        return Stream.of(EColor.values())
                .map(PetColorCategory::of)
                .filter(x -> {
                    if(StringUtils.hasText(search)) {
                        return x.name().toLowerCase().contains(search.toLowerCase());
                    }

                    return true;
                })
                .toList();
    }
}

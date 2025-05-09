package com.paw.fund.app.modules.category_management.service.pet.gender;

import com.paw.fund.app.modules.category_management.domain.PetGenderCategory;
import com.paw.fund.enums.EPetGender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

@Service
public class PetGenderCategoryQueryService {
    public List<PetGenderCategory> findAll(String search) {
        return Stream.of(EPetGender.values())
                .map(PetGenderCategory::of)
                .filter(x -> {
                    if(StringUtils.hasText(search)) {
                        return x.name().toLowerCase().contains(search.toLowerCase());
                    }

                    return true;
                })
                .toList();
    }
}

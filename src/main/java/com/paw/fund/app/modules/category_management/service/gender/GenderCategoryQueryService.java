package com.paw.fund.app.modules.category_management.service.gender;

import com.paw.fund.app.modules.category_management.domain.GenderCategory;
import com.paw.fund.enums.EGender;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GenderCategoryQueryService {
    public List<GenderCategory> findAll(String search) {
        return Stream.of(EGender.values())
                .filter(x -> !StringUtils.hasText(search)
                    || x.getName().toLowerCase().contains(search.toLowerCase()))
                .map(GenderCategory::of)
                .toList();
    }
}

package com.paw.fund.app.modules.category_management.service.role;

import com.paw.fund.app.modules.category_management.domain.RoleCategory;
import com.paw.fund.enums.ERole;
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
public class RoleCategoryQueryService {

    public List<RoleCategory> findAll(String search) {
        return Stream.of(ERole.values())
                .filter(x -> !StringUtils.hasText(search)
                        || x.getName().toLowerCase().contains(search.toLowerCase()))
                .map(RoleCategory::of)
                .toList();
    }
}

package com.paw.fund.app.modules.category_management.service.license.type;

import com.paw.fund.app.modules.category_management.domain.LicenseTypeCategory;
import com.paw.fund.enums.ELicenseType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LicenseTypeCategoryQueryService {
    public List<LicenseTypeCategory> findAll(String search) {
        return Stream.of(ELicenseType.values())
                .map(LicenseTypeCategory::of)
                .filter(x -> !StringUtils.hasText(search)
                        || x.name().toLowerCase().contains(search))
                .toList();
    }
}

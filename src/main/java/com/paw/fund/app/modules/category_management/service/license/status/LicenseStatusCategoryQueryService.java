package com.paw.fund.app.modules.category_management.service.license.status;

import com.paw.fund.app.modules.category_management.domain.LicenseStatusCategory;
import com.paw.fund.enums.ELicenseStatus;
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
public class LicenseStatusCategoryQueryService {
    public List<LicenseStatusCategory> findAll(String search) {
        return Stream.of(ELicenseStatus.values())
                .map(LicenseStatusCategory::of)
                .filter(x -> !StringUtils.hasText(search)
                        || x.name().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }
}

package com.paw.fund.app.modules.category_management.service.shelter.registration;

import com.paw.fund.app.modules.category_management.domain.ShelterRegistrationStatusCategory;
import com.paw.fund.enums.EShelterRegistrationStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ShelterRegistrationStatusCategoryQueryService {
    public List<ShelterRegistrationStatusCategory> findAll(String search) {
        return Stream.of(EShelterRegistrationStatus.values())
                .map(ShelterRegistrationStatusCategory::of)
                .filter(status -> {
                    if(StringUtils.hasText(search)) {
                        return status.name().toLowerCase().contains(search.toLowerCase());
                    }

                    return true;
                })
                .toList();
    }
}

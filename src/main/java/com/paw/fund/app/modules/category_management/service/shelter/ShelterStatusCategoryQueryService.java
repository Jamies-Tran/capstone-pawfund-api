package com.paw.fund.app.modules.category_management.service.shelter;

import com.paw.fund.app.modules.category_management.domain.ShelterStatusCategory;
import com.paw.fund.enums.EShelterStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ShelterStatusCategoryQueryService {
    public List<ShelterStatusCategory> findAll(String search) {
        return Stream.of(EShelterStatus.values())
                .map(ShelterStatusCategory::of)
                .filter(status -> {
                    if(StringUtils.hasText(search)) {
                        return status.name().toLowerCase().contains(search.toLowerCase());
                    }

                    return true;
                })
                .toList();
    }
}

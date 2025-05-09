package com.paw.fund.app.modules.category_management.service.pet.information.status;

import com.paw.fund.app.modules.category_management.domain.PetInformationStatusCategory;
import com.paw.fund.enums.EPetInformationStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

@Service
public class PetInformationStatusCategoryQueryService {
    public List<PetInformationStatusCategory> findAll(String search) {
        return Stream.of(EPetInformationStatus.values())
                .map(PetInformationStatusCategory::of)
                .filter(x -> {
                    if(StringUtils.hasText(search)) {
                        return x.name().toLowerCase().contains(search.toLowerCase());
                    }

                    return true;
                })
                .toList();
    }
}

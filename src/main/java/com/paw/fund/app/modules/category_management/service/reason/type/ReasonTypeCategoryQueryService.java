package com.paw.fund.app.modules.category_management.service.reason.type;

import com.paw.fund.app.modules.category_management.domain.ReasonTypeCategory;
import com.paw.fund.enums.EReasonType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ReasonTypeCategoryQueryService {
    public List<ReasonTypeCategory> findAll(String search) {
        return Stream.of(EReasonType.values())
                .map(ReasonTypeCategory::of)
                .filter(x -> {
                    if(StringUtils.hasText(search)) {
                        return x.name().toLowerCase().contains(search.toLowerCase());
                    }

                    return true;
                })
                .toList();
    }
}

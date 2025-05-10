package com.paw.fund.app.modules.category_management.service.receive.source;

import com.paw.fund.app.modules.category_management.domain.ReceiveSourceCategory;
import com.paw.fund.enums.EReceiveSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ReceiveSourceQueryService {
    public List<ReceiveSourceCategory> findAll(String search) {
        return Stream.of(EReceiveSource.values())
                .map(ReceiveSourceCategory::of)
                .filter(x -> {
                    if(StringUtils.hasText(search)) {
                        return x.name().toLowerCase().contains(search.toLowerCase());
                    }

                    return true;
                })
                .toList();
    }
}

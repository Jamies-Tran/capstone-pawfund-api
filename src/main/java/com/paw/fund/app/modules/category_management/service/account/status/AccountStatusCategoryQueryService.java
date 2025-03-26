package com.paw.fund.app.modules.category_management.service.account.status;

import com.paw.fund.app.modules.category_management.domain.AccountStatusCategory;
import com.paw.fund.enums.EAccountStatus;
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
public class AccountStatusCategoryQueryService {
    public List<AccountStatusCategory> findAll(String search) {
        return Stream.of(EAccountStatus.values())
                .filter(x -> !StringUtils.hasText(search)
                    || x.getName().toLowerCase().contains(search.toLowerCase()))
                .map(AccountStatusCategory::of)
                .toList();
    }
}

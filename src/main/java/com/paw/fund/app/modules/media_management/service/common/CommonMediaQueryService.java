package com.paw.fund.app.modules.media_management.service.common;

import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.media_management.domain.common.ICommonMediaMapper;
import com.paw.fund.app.modules.media_management.repository.database.common.CommonMediaEntity;
import com.paw.fund.app.modules.media_management.repository.database.common.ICommonMediaRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommonMediaQueryService {
    @NonNull
    ICommonMediaRepository repository;

    @NonNull
    ICommonMediaMapper mapper;

    public List<CommonMedia> findAllByAccountId(Long accountId) {
        List<CommonMediaEntity> foundCommonMedias = repository.findAllByAccountId(accountId);

        return foundCommonMedias.stream()
                .map(mapper::toDto)
                .toList();
    }
}

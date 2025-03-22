package com.paw.fund.app.modules.media_management.service.common;

import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.media_management.domain.common.ICommonMediaMapper;
import com.paw.fund.app.modules.media_management.repository.database.common.CommonMediaEntity;
import com.paw.fund.app.modules.media_management.repository.database.common.ICommonMediaRepository;
import com.paw.fund.enums.EMimeType;
import com.paw.fund.utils.image.ImageUtil;
import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommonMediaCommandService {
    @Nonnull
    ICommonMediaRepository repository;

    @NonNull
    ICommonMediaMapper mapper;

    public List<CommonMedia> saveAllForAccount(Long accountId, List<CommonMedia> medias) {
        List<CommonMediaEntity> newMedias = medias.stream()
                .map(x -> {
                    EMimeType mimeType = ImageUtil.findMimeType(x.url());
                    return x
                            .withAccountId(accountId)
                            .withMediaTypeCode(mimeType.getType())
                            .withMediaTypeName(mimeType.getName());
                })
                .map(mapper::toEntity)
                .toList();
        List<CommonMediaEntity> savedMedias = repository.saveAll(newMedias);

        return savedMedias
                .stream()
                .map(mapper::toDto)
                .toList();
    };

    public void deleteAllByAccountId(Long accountId) {
        List<CommonMediaEntity> commonMedias = repository.findAllByAccountId(accountId);
        repository.deleteAll(commonMedias);
    }
}

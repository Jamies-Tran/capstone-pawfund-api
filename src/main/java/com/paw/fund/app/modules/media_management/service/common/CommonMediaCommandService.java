package com.paw.fund.app.modules.media_management.service.common;

import com.paw.fund.app.modules.form_management.domain.option.Option;
import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.media_management.domain.common.ICommonMediaMapper;
import com.paw.fund.app.modules.media_management.domain.common.event.listener.CreateCommonMediaListener;
import com.paw.fund.app.modules.media_management.repository.database.common.CommonMediaEntity;
import com.paw.fund.app.modules.media_management.repository.database.common.ICommonMediaRepository;
import com.paw.fund.common.aspect.annotation.validate.args.ValidateArgs;
import com.paw.fund.enums.EMimeType;
import com.paw.fund.utils.CollectionUtils;
import com.paw.fund.utils.ObjectUtils;
import com.paw.fund.utils.image.ImageUtil;
import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommonMediaCommandService {
    @Nonnull
    ICommonMediaRepository repository;

    @NonNull
    ICommonMediaMapper mapper;

    @ValidateArgs
    protected List<CommonMedia> saveAllWithAccountId(Long accountId, List<CommonMedia> medias) {
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

    protected List<CommonMedia> updateAllByAccountId(Long accountId, List<CommonMedia> medias) {
        List<CommonMediaEntity> foundMedias = repository.findAllByAccountId(accountId);
        Map<Long, CommonMediaEntity> foundMediaMap = foundMedias.stream()
                .collect(Collectors.toMap(CommonMediaEntity::getCommonMediaId, media -> media));
        List<Long> foundIds = foundMedias.stream()
                .map(CommonMediaEntity::getCommonMediaId)
                .toList();

        List<CommonMediaEntity> updateMedias = medias.stream()
                .filter(media -> ObjectUtils.isNotNull(media.commonMediaId())
                        && CollectionUtils.contains(foundIds, media.commonMediaId()))
                .map(media -> {
                    CommonMediaEntity foundMedia = foundMediaMap.get(media.commonMediaId());
                    mapper.update(foundMedia, media);

                    return foundMedia;
                })
                .toList();
        List<CommonMediaEntity> newMedias = medias.stream()
                .filter(media -> ObjectUtils.isNull(media.commonMediaId())
                        || CollectionUtils.notContains(foundIds, media.commonMediaId()))
                .map(media -> {
                    EMimeType mimeType = ImageUtil.findMimeType(media.url());
                    return media
                            .withAccountId(accountId)
                            .withMediaTypeCode(mimeType.getType())
                            .withMediaTypeName(mimeType.getName());
                })
                .map(mapper::toEntity)
                .toList();

        List<CommonMediaEntity> saveMedias = Stream.concat(updateMedias.stream(), newMedias.stream()).toList();
        return repository.saveAll(saveMedias)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}

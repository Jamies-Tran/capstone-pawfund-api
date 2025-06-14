package com.paw.fund.app.modules.media_management.service.common;

import com.paw.fund.app.modules.form_management.domain.option.Option;
import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import com.paw.fund.app.modules.media_management.domain.common.ICommonMediaMapper;
import com.paw.fund.app.modules.media_management.domain.common.event.listener.CreateCommonMediaListener;
import com.paw.fund.app.modules.media_management.repository.database.common.CommonMediaEntity;
import com.paw.fund.app.modules.media_management.repository.database.common.ICommonMediaRepository;
import com.paw.fund.common.aspect.annotation.validate.args.ValidateArgs;
import com.paw.fund.enums.EMimeType;
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

    protected void deleteAllByAccountId(Long accountId) {
        List<CommonMediaEntity> commonMedias = repository.findAllByAccountId(accountId);
        repository.deleteAll(commonMedias);
    }

    protected List<CommonMedia> saveAllWithShelterId(Long shelterId, List<CommonMedia> medias) {
        List<CommonMediaEntity> newMedias = medias.stream()
                .map(x -> {
                    EMimeType mimeType = ImageUtil.findMimeType(x.url());
                    return x
                            .withShelterId(shelterId)
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

    public List<CommonMedia> saveAllWithPetId(Long petId, List<CommonMedia> medias) {
        List<CommonMediaEntity> newMedias = medias.stream()
                .map(x -> {
                    EMimeType mimeType = ImageUtil.findMimeType(x.url());
                    return x.withPetId(petId)
                            .withMediaTypeCode(mimeType.getType())
                            .withMediaTypeName(mimeType.getName());
                })
                .map(mapper::toEntity)
                .toList();
        List<CommonMediaEntity> saveMedias = repository.saveAll(newMedias);

        return saveMedias.stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<CommonMedia> updateAllByPetId(Long petId, List<CommonMedia> medias) {
        List<CommonMediaEntity> existedMedias = repository.findAllByPetId(petId);
        List<Long> deletedIds = existedMedias.stream()
                .map(CommonMediaEntity::getCommonMediaId)
                .filter(x -> medias.stream().noneMatch(xx ->  Optional
                        .ofNullable(xx.commonMediaId())
                        .orElse(Long.MIN_VALUE).equals(x)))
                .toList();
        repository.deleteAllById(deletedIds);

        Map<Long, CommonMediaEntity> existedMediaMap = existedMedias.stream()
                .collect(Collectors.toMap(CommonMediaEntity::getCommonMediaId, x -> x));
        List<CommonMediaEntity> newCommonMedias = medias.stream()
                .map(x -> {
                    CommonMediaEntity media;
                    EMimeType mimeType = ImageUtil.findMimeType(x.url());
                    if(Objects.nonNull(x.commonMediaId())) {
                        media = existedMediaMap.computeIfAbsent(x.commonMediaId(), _ -> {
                            CommonMediaEntity altMedia = mapper.toEntity(x
                                    .withPetId(petId)
                                    .withMediaTypeCode(mimeType.getCode())
                                    .withMediaTypeName(mimeType.getName()));
                            altMedia.setCommonMediaId(null);

                            return altMedia;
                        });

                    } else {
                        media = mapper.toEntity(x
                                .withPetId(petId)
                                .withMediaTypeCode(mimeType.getType())
                                .withMediaTypeName(mimeType.getName()));
                    }
                    mapper.update(media, x);

                    return media;
                })
                .toList();
        List<CommonMediaEntity> saveMedias = repository.saveAll(newCommonMedias);

        return saveMedias.stream()
                .map(mapper::toDto)
                .toList();
    }
}

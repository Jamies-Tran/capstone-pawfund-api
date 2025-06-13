package com.paw.fund.app.modules.media_management.service.verification;

import com.paw.fund.app.modules.media_management.domain.verification.IVerificationMediaMapper;
import com.paw.fund.app.modules.media_management.domain.verification.VerificationMedia;
import com.paw.fund.app.modules.media_management.repository.database.verification.IVerificationMediaRepository;
import com.paw.fund.app.modules.media_management.repository.database.verification.VerificationMediaEntity;
import com.paw.fund.enums.EMimeType;
import com.paw.fund.utils.image.ImageUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VerificationMediaCommandService {
    @NonNull
    IVerificationMediaRepository repository;

    @NonNull
    IVerificationMediaMapper mapper;

    public List<VerificationMedia> saveAllWithPetIntakeRegistrationId(Long petIntakeRegistrationId,
                                                                      List<VerificationMedia> verificationMedias) {

        List<VerificationMediaEntity> newVerificationMedias = verificationMedias.stream()
                .map(x -> {
                    EMimeType mimeType = ImageUtil.findMimeType(x.url());
                    return mapper.toEntity(x
                            .withPetIntakeRegistrationId(petIntakeRegistrationId)
                            .withMediaTypeCode(mimeType.getCode())
                            .withMediaTypeName(mimeType.getName()));
                })
                .toList();

        return repository.saveAll(newVerificationMedias)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<VerificationMedia> saveAllWithAdoptRegistrationId(Long adoptRegistrationId,
                                                                  List<VerificationMedia> verificationMedias) {

        List<VerificationMediaEntity> newVerificationMedias = verificationMedias.stream()
                .map(x -> {
                    EMimeType mimeType = ImageUtil.findMimeType(x.url());
                    return mapper.toEntity(x
                            .with(petIntakeRegistrationId)
                            .withMediaTypeCode(mimeType.getCode())
                            .withMediaTypeName(mimeType.getName()));
                })
                .toList();

        return repository.saveAll(newVerificationMedias)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<VerificationMedia> updateAllWithPetIntakeRegistrationId(Long petIntakeRegistrationId, List<VerificationMedia> medias) {
        List<VerificationMediaEntity> existedMedias = repository.findAllByPetIntakeRegistrationId(petIntakeRegistrationId);
        List<Long> deletedIdList = existedMedias.stream()
                .map(VerificationMediaEntity::getVerificationMediaId)
                .filter(x -> medias.stream().filter(y -> Objects.nonNull(y.verificationMediaId()))
                        .noneMatch(y -> y.verificationMediaId().equals(x)))
                .toList();
        repository.deleteAllById(deletedIdList);
        Map<Long, VerificationMediaEntity> existedMediaMap = existedMedias.stream()
                .filter(x -> Objects.nonNull(x.getVerificationMediaId()))
                .collect(Collectors.toMap(VerificationMediaEntity::getVerificationMediaId, x -> x));
        List<VerificationMediaEntity> updateMedias = medias.stream()
                .filter(x -> Objects.nonNull(x.verificationMediaId()))
                .map(x -> {
                    VerificationMediaEntity foundMedia = existedMediaMap.computeIfAbsent(x.verificationMediaId(), _ -> null);
                    if(Objects.nonNull(foundMedia)) {
                        mapper.update(foundMedia, x);
                        EMimeType mimeType = ImageUtil.findMimeType(foundMedia.getUrl());
                        foundMedia.setMediaTypeCode(mimeType.getCode());
                        foundMedia.setMediaTypeName(mimeType.getName());

                        return foundMedia;
                    }

                    EMimeType mimeType = ImageUtil.findMimeType(x.url());
                    return mapper.toEntity(x
                            .withPetIntakeRegistrationId(petIntakeRegistrationId)
                            .withMediaTypeCode(mimeType.getCode())
                            .withMediaTypeName(mimeType.getName()));
                })
                .toList();

        List<VerificationMediaEntity> newMedias = medias.stream()
                .filter(x -> Objects.isNull(x.verificationMediaId()))
                .map(x -> {
                    EMimeType mimeType = ImageUtil.findMimeType(x.url());

                    return mapper.toEntity(x
                            .withPetIntakeRegistrationId(petIntakeRegistrationId)
                            .withMediaTypeCode(mimeType.getCode())
                            .withMediaTypeName(mimeType.getName()));
                })
                .toList();
        List<VerificationMediaEntity> savedMedias = repository
                .saveAll(Stream.concat(updateMedias.stream(), newMedias.stream()).toList());

        return savedMedias.stream()
                .map(mapper::toDto)
                .toList();
    }
}

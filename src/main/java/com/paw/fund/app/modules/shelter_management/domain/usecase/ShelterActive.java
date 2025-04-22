package com.paw.fund.app.modules.shelter_management.domain.usecase;

import com.paw.fund.app.modules.media_management.domain.common.CommonMedia;
import lombok.Builder;

import java.util.List;

@Builder
public record ShelterActive(
        String description,
        Long shelterId,
        List<CommonMedia> medias
) {
    public static ShelterActive of(String description,
                                   Long shelterId,
                                   List<CommonMedia> medias) {
        return ShelterActive.builder()
                .description(description)
                .shelterId(shelterId)
                .medias(medias)
                .build();
    }
}

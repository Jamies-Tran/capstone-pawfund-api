package com.paw.fund.app.modules.pet_management.domain.hobby.usecase;

import com.paw.fund.app.modules.pet_management.domain.hobby.Hobby;
import lombok.Builder;

import java.util.List;

@Builder
public record HobbyList(List<Hobby> list) {
    public static HobbyList of(List<Hobby> list) {
        return HobbyList.builder()
                .list(list)
                .build();
    }
}

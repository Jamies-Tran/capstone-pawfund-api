package com.paw.fund.enums;

import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EShelterRegistrationStatus {
    NEW("NEW", "Mới"),
    RECEIVED("RECEIVED", "Đã tiếp nhận"),
    APPROVED("APPROVED", "Đã duyệt"),
    REJECTED("REJECTED", "Đã từ chối"),;

    String code;
    String name;

    public static EShelterRegistrationStatus findByCode(String code) {
        return Stream.of(values())
                .filter(x -> Objects.equals(x.getCode(), code))
                .findAny()
                .orElseThrow(ResourceNotFoundException::new);
    }
}

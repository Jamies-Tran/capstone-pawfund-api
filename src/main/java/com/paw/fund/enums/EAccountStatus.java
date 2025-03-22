package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EAccountStatus {
    ACTIVE("ACTIVE", "Kích hoạt"),
    INACTIVE("INACTIVE", "Chưa kích hoạt"),;

    String code;
    String name;

    public static EAccountStatus findByCode(String code) {
        return Stream.of(values())
                .filter(x -> Objects.equals(x.getCode(), code))
                .findAny()
                .orElse(null);
    }
}

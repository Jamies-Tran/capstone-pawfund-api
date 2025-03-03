package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EGender {
    MALE("MALE", "Nam"),
    FEMALE("FEMALE", "Nữ");

    String code;
    String name;
}

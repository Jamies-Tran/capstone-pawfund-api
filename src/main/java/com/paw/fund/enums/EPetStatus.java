package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EPetStatus {
    ADOPTABLE("ADOPTABLE", "Có thể nhận nuôi"),
    NOT_ADOPTABLE("NOT_ADOPTABLE", "Chưa thể nhận nuôi"),
    ADOPTED("ADOPTED", "Đã nhận nuôi"),
    NONE("NONE", ""),;

    String code;
    String name;
}

package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EStatus {
    ENABLE("ENABLE", "Kích hoạt"),
    DISABLE("DISABLE", "Vô hiệu hóa"),
    DELETED("DELETED", "Đã xóa");

    String code;
    String name;
}

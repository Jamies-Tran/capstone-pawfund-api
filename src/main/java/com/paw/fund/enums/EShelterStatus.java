package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EShelterStatus {
    DRAFT("DRAFT", "Nháp"),
    ENABLE("ENABLE", "Đang hoạt động"),
    DISABLE("DISABLE", "Vô hiệu hóa"),
    BANNED("BANNED", "Cấm hoạt động");

    String code;
    String name;
}

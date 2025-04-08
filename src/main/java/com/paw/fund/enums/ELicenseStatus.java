package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ELicenseStatus {
    ACTIVE("ACTIVE", "Kích hoạt"),
    INACTIVE("INACTIVE", "Ngừng hoạt động");

    String code;
    String name;
}

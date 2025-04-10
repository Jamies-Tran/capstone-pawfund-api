package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ELicenseType {
    ANIMAL_RESCUE_CENTER_OPERATING_LICENSE("ANIMAL_RESCUE_CENTER_OPERATING_LICENSE", "Giấy phép hoạt động trung tâm cứu hộ động vật"),;

    String code;
    String name;
}

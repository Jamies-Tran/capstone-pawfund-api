package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EReceiveSource {
    CUSTOMER("CUSTOMER", "Từ khách hàng"),
    STAFF("STAFF", "Từ đội ngũ nhân viên");

    String code;
    String name;
}

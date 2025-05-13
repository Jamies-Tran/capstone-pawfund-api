package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EReasonType {
    RESCUE("RESCUE", "Giải cứu"),
    INTAKE_FROM_WILD("INTAKE_FROM_WILD", "Tiếp nhận động vật hoang"),;

    String code;
    String name;
}

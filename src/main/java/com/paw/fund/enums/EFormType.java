package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EFormType {
    SHELTER_REGISTER("SHELTER_REGISTER", "Đăng ký trung tâm cứu trợ");

    String code;
    String name;
}

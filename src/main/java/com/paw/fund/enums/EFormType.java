package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EFormType {
    SHELTER_REGISTER("SHELTER_REGISTER", "Đăng ký trung tâm cứu trợ"),
    ADOPT_REGISTER("ADOPT_REGISTER", "Đăng ký nhận nuôi thú cưng"),;

    String code;
    String name;
}

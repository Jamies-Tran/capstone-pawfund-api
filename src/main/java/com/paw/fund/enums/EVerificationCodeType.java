package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EVerificationCodeType {
    ACCOUNT_CREATION("ACCOUNT_CREATION", "Xác minh tài khoản"),
    EMAIL_UPDATE("EMAIL_UPDATE", "Xác minh thay đổi email");


    String code;
    String name;
}

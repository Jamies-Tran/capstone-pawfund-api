package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ELoginStatus {
    LOGIN("LOGIN", "Đăng nhập"),
    LOGOUT("LOGOUT", "Đăng xuất");

    String code;
    String name;
}

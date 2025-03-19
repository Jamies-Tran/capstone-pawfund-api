package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EAction {
    CREATED("CREATED", "Tạo mới"),
    SEND_VERIFIED("SEND_VERIFIED", "Gửi mã xác thức"),
    VERIFIED("VERIFIED", "Xác thực"),
    LOGIN("LOGIN", "Đăng nhập"),
    LOGOUT("LOGOUT", "Đăng xuất"),;

    String code;
    String name;
}

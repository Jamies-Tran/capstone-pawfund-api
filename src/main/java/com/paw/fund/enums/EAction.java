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
    SEND_VERIFIED_ACCOUNT("SEND_VERIFIED_ACCOUNT", "Gửi mã xác thức tài khoản"),
    SEND_VERIFIED_EMAIL("SEND_VERIFIED_EMAIL", "Gửi mã xác thức email"),
    VERIFIED_ACCOUNT("VERIFIED_ACCOUNT", "Đã xác thực tài khoản"),
    VERIFIED_EMAIL("VERIFIED_EMAIL", "Đã xác thực email"),
    LOGIN("LOGIN", "Đăng nhập"),
    LOGOUT("LOGOUT", "Đăng xuất"),;

    String code;
    String name;
}

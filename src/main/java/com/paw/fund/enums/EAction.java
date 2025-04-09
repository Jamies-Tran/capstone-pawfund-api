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
    SEND_VERIFIED_ACCOUNT("SEND_VERIFIED_ACCOUNT", "Gửi mã xác thức tài khoản cá nhân"),
    SEND_VERIFIED_EMAIL("SEND_VERIFIED_EMAIL", "Gửi mã xác thực email cá nhân"),
    VERIFIED_ACCOUNT("VERIFIED_ACCOUNT", "Đã xác thực tài khoản cá nhân"),
    VERIFIED_EMAIL("VERIFIED_EMAIL", "Đã xác thực email"),
    LOGIN("LOGIN", "Đăng nhập"),
    LOGOUT("LOGOUT", "Đăng xuất"),
    DELETE_ACCOUNT("DELETE_ACCOUNT", "Xóa tài khoản người dùng"),
    ACTIVE_ACCOUNT("ACTIVE_ACCOUNT", "Kích hoạt tài khoản người dùng"),
    INACTIVE_ACCOUNT("INACTIVE_ACCOUNT", "Vô hiệu hóa tài khoản người dùng"),
    SELF_CHANGE_PASS("SELF_CHANGE_PASS", "Đổi mật khẩu tài khoản cá nhân"),
    SELF_CHANGE_INFO("SELF_CHANGE_INFO", "Đổi thông tin cá nhân"),
    CHANGE_PASS("CHANGE_PASS", "Đổi mật khẩu tài khoản người dùng"),
    SELF_UPDATE("SELF_UPDATE", "Cập nhật thông tin cá nhân"),
    NOT_DEFINE("NOT_DEFINE", "Hành động chưa xác định"),
    REGISTER_ADOPTER("REGISTER_ADOPTER", "Đăng ký người nhận nuôi"),
    CREATE_LICENSE("CREATE_LICENSE", "Tạo giấy phép"),
    UPDATE_LICENSE("UPDATE_LICENSE", "Cập nhật giấy phép"),
    ACTIVE_LICENSE("ACTIVE_LICENSE", "Kích hoạt giấy phép"),
    INACTIVE_LICENSE("INACTIVE_LICENSE", "Vô hiệu hóa giấy phép"),
    DELETE_LICENSE("DELETE_LICENSE", "Xóa giấy phép"),;

    String code;
    String name;
}

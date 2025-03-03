package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ERole {
    ADMIN("ADMIN", "Quản trị viên"),
    SHELTER_OWNER("SHELTER_OWNER", "Chủ sở hữu trung tâm cứu hộ"),
    ADOPTER("ADOPTER", "Người nhận nuôi"),
    DONOR("DONOR", "Người quyên góp"),
    STAFF("STAFF", "Nhân viên trung tâm cứu trợ");

    String code;
    String name;
}

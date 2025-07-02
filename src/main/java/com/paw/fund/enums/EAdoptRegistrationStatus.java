package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EAdoptRegistrationStatus {
    NEW("NEW", "Mới"),
    IN_PROGRESS("IN_PROGRESS", "Đang xử lý"),
    APPROVED("APPROVED", "Đã duyệt"),
    DENY("DENY", "Đã từ chối");

    String code;
    String name;
}

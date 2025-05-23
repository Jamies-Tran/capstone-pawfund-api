package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EShelterAssignmentStatus {
    NEW("NEW", "Mới"),
    RECEIVED("RECEIVED", "Tiếp nhận"),
    CANCELED("CANCELED", "Hủy bỏ"),
    COMPLETED("COMPLETED", "Hoàn thành");

    String code;
    String name;
}

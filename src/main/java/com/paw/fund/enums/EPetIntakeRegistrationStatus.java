package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EPetIntakeRegistrationStatus {
    NEW("NEW", "Mới"),
    PROCESSING("PROCESSING", "Đang xử lý"),
    FINISHED("FINISHED", "Đã xử lý"),
    CANCEL("CANCEL", "Hủy");

    String code;
    String name;
}

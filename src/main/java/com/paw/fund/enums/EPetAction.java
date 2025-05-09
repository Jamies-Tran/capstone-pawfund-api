package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EPetAction {
    CREATED("CREATED", "Thêm mới"),
    UPDATED("UPDATED", "Cập nhật thông tin"),
    DELETED("DELETED", "Xóa"),
    UPDATED_HEALTH_STATUS("UPDATED_HEALTH_STATUS", "Cập nhật trạng thái sức khỏe"),
    UPDATED_STATUS("UPDATED_STATUS", "Cập nhật trạng thái"),;

    String code;
    String name;
}

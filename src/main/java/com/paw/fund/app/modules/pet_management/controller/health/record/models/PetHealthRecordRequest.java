package com.paw.fund.app.modules.pet_management.controller.health.record.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PetHealthRecordRequest(
        @NotNull(message = "Thông tin thú cưng không được bỏ trống")
        Long petId,

        @NotNull(message = "Ngày khám thú cưng không được bỏ trống")
        LocalDateTime checkupDate,

        @NotNull(message = "Cân nặng thú cưng không được bỏ trống")
        BigDecimal weight,

        @NotNull(message = "Mô tả tình trạng thú cưng không được bỏ trống")
        String conditionDescription,

        String treatment,

        String diagnosis,

        @NotNull(message = "Mã tình trạng sức khỏe của thú cưng không được bỏ trống")
        String healthStatusCode,

        @NotNull(message = "Tên tình trạng sức khỏe của thú cưng không được bỏ trống")
        String healthStatusName
) {
}

package com.paw.fund.app.modules.shelter_management.controller.api.registration.models;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ShelterRegistrationRequest(
        @NotNull(message = "Tên trung tâm cứu trợ không được bỏ trống")
        String shelterName,

        @NotNull(message = "Địa chỉ không được bỏ trống")
        String placeId,

        @NotNull(message = "Chưa có biểu mẫu khảo sát")
        Long formResponseId,

        @NotNull(message = "Đường dây nóng không được bỏ trống")
        String hotline,

        @NotNull(message = "Email trung tâm cứu trợ không được bỏ trống")
        String email,

        LocalDateTime dateOfPub
) {
}

package com.paw.fund.app.modules.account_management.controller.models;

import com.paw.fund.app.modules.account_management.controller.models.medias.CommonMediaRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

public record AccountUpdateRequest(
        @Schema(description = "Họ của người dùng", example = "Nguyễn")
        @NotNull(message = "Vui lập nhập họ tên")
        String firstName,
        @Schema(description = "Tên hoặc tên đệm người dùng", example = "Văn Bê")
        @NotNull(message = "Vui lập nhập tên")
        String lastName,
        @Schema(description = "Số CCCD của người dùng", example = "079097015437")
        @NotNull(message = "Vui lập nhập số cccd")
        @Length(min = 12, message = "Số CCCD phải có ít nhất 12 ký tự số")
        String identification,
        @Schema(description = "Số điện thoại của người dùng", example = "0981874736")
        @NotNull(message = "Vui lập nhập số điện thoại")
        @Pattern(regexp = "^(?:\\+84|0)(3[2-9]|5[2689]|7[0-9]|8[1-9]|9[0-9])[0-9]{7}$", message = "Số điện thoại không hợp lệ")
        String phone,
        @Schema(description = "Địa chỉ của người dùng", example = "43 Phan Văn Trị, phường 2, quận 5, TP Hồ Chí Minh")
        String address,
        @Schema(description = "Ngày sinh của người dùng", example = "1997-01-02")
        LocalDate dateOfBirth,
        @Schema(description = "Mã giới tính", example = "MALE")
        @NotNull(message = "Vui lòng chọn thông tin giới tính")
        String genderCode,
        @Schema(description = "Tên giới tính", example = "Nam")
        @NotNull(message = "Vui lòng chọn thông tin giới tính")
        String genderName,
        @Schema(description = "Ảnh đại diện")
        @Valid
        List<CommonMediaRequest> medias
) {
}

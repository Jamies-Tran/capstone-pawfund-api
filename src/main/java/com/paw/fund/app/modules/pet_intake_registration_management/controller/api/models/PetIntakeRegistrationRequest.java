package com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models;

import com.paw.fund.app.modules.pet_intake_registration_management.controller.api.models.media.VerificationMediaRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.util.List;

@Builder
public record PetIntakeRegistrationRequest(
        @NotNull(message = "Vui lòng thêm thông tin loại thú cưng")
        Long petTypeId,
        @NotNull(message = "Vui lòng thêm thông tin vị trí yêu cầu đăng ký tiếp nhận thú cưng")
        String placeId,
        @NotNull(message = "Vui lòng nhập số điện thoại của người thông báo")
        String informerPhone,
        String petDescription,
        @NotNull(message = "Vui lòng thêm mã lý do gửi đăng ký tiếp nhận thú cưng")
        String reasonTypeCode,
        @NotNull(message = "Vui lòng thêm tên lý do gửi đăng ký tiếp nhận thú cưng")
        String reasonTypeName,
        @Valid @NotNull(message = "Vui lòng thêm hình ảnh xác nhận thú cưng được yêu cầu tiếp nhận")
        List<VerificationMediaRequest> medias
) {
}

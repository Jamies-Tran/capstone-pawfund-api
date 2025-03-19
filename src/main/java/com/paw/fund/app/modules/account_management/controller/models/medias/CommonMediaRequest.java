package com.paw.fund.app.modules.account_management.controller.models.medias;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CommonMediaRequest(
        @NotNull(message = "Vui lòng nhập url hình ảnh")
        @Schema(description = "Đường dẫn hình ảnh", example = "https://firebasestorage.googleapis.com/v0/b/stay-with-me-356017.appspot.com/o/admin%2Favatar%2Fdefault.png?alt=media&token=03023503-c815-4229-bba1-13c9e7b22981")
        String url,
        @Schema(description = "Đánh dấu ảnh thumbnail")
        Boolean isThumbnail
) {
}

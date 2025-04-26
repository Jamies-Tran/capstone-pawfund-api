package com.paw.fund.app.modules.form_management.controller.form.v1.models.form;

import com.paw.fund.app.modules.form_management.controller.form.v1.models.question.QuestionUpdateRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record FormUpdateRequest(
        @NotNull(message = "Vui lòng nhập tiêu đề của form")
        @Schema(description = "Tiêu đề của form")
        String title,
        @Schema(description = "Mô tả của form")
        String description,
        @Schema(description = "DS câu hỏi của form")
        List<QuestionUpdateRequest> questions
) {
}

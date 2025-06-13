package com.paw.fund.app.modules.form_management.controller.form.v1.models.form;

import com.paw.fund.app.modules.form_management.controller.form.v1.models.question.QuestionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record FormRequest(
        Long shelterId,
        @NotNull(message = "Vui lòng nhập tiêu đề của form")
        @Schema(description = "Tiêu đề của form (required)")
        String title,
        @Schema(description = "Mô tả của form")
        String description,
        @Size(min = 1, message = "Phải có ít nhất 1 câu hỏi")
        @Schema(description = "DS câu hỏi của form")
        List<QuestionRequest> questions
) {
}

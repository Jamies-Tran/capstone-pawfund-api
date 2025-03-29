package com.paw.fund.app.modules.form_management.controller.models.form;

import com.paw.fund.app.modules.form_management.controller.models.question.QuestionRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record FormRequest(
        @NotNull(message = "Vui lòng nhập tiêu đề của form")
        String title,
        String description,
        @Size(min = 1, message = "Phải có ít nhất 1 câu hỏi")
        List<QuestionRequest> questions
) {
}

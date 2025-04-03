package com.paw.fund.app.modules.form_management.controller.models.form;

import com.paw.fund.app.modules.form_management.controller.models.question.QuestionRequest;
import com.paw.fund.app.modules.form_management.controller.models.question.QuestionUpdateRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

@Builder
public record FormUpdateRequest(
        @NotNull(message = "Vui lòng nhập tiêu đề của form")
        String title,
        String description,
        List<QuestionUpdateRequest> questions
) {
}

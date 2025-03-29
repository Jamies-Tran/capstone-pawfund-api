package com.paw.fund.app.modules.form_management.controller.models.question;

import com.paw.fund.app.modules.form_management.controller.models.option.OptionRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record QuestionRequest(
        String questionText,
        List<OptionRequest> options,
        @NotNull(message = "Vui lòng chọn loại câu hỏi")
        String questionTypeCode,
        @NotNull(message = "Vui lòng chọn loại câu hỏi")
        String questionTypeName
) {
}

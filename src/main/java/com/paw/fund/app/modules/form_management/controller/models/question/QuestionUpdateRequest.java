package com.paw.fund.app.modules.form_management.controller.models.question;

import com.paw.fund.app.modules.form_management.controller.models.option.OptionRequest;
import com.paw.fund.app.modules.form_management.controller.models.option.OptionUpdateRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record QuestionUpdateRequest(
        Long questionId,
        String questionText,
        List<OptionUpdateRequest> options,
        @NotNull(message = "Vui lòng chọn loại câu hỏi")
        String questionTypeCode,
        @NotNull(message = "Vui lòng chọn loại câu hỏi")
        String questionTypeName
) {
}

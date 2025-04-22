package com.paw.fund.app.modules.form_management.controller.form.v1.models.question;

import com.paw.fund.app.modules.form_management.controller.form.v1.models.option.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record QuestionRequest(
        @NotNull(message = "Vui lòng nhập nội dung câu hỏi")
        @Schema(description = "Nội dung câu hỏi")
        String questionText,
        @Schema(description = "DS các lựa chọn câu trả lời (Nếu thuộc loại MULTIPLE_CHOICE - Trắc nghiệm)")
        List<OptionRequest> options,
        @NotNull(message = "Vui lòng chọn loại câu hỏi")
        @Schema(description = "Mã loại câu hỏi (Category loại câu hỏi)")
        String questionTypeCode,
        @NotNull(message = "Tên loại câu hỏi")
        @Schema(description = "Tên loại câu hỏi (Category loại câu hỏi)")
        String questionTypeName
) {
}

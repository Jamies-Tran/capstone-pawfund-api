package com.paw.fund.app.modules.form_management.controller.models.question;

import com.paw.fund.app.modules.form_management.controller.models.option.OptionRequest;
import com.paw.fund.app.modules.form_management.controller.models.option.OptionUpdateRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record QuestionUpdateRequest(
        @Schema(description = "ID của câu hỏi (nếu null thì hệ thống sẽ tự hiểu là thêm mới)")
        Long questionId,
        @Schema(description = "Nội dung câu hỏi")
        String questionText,
        @Schema(description = "DS các lựa chọn câu trả lời (Nếu thuộc loại MULTIPLE_CHOICE - Trắc nghiệm)")
        List<OptionUpdateRequest> options,
        @NotNull(message = "Vui lòng chọn loại câu hỏi")
        @Schema(description = "Mã loại câu hỏi (Category loại câu hỏi)")
        String questionTypeCode,
        @NotNull(message = "Vui lòng chọn loại câu hỏi")
        @Schema(description = "Tên loại câu hỏi (Category loại câu hỏi)")
        String questionTypeName
) {
}

package com.paw.fund.app.modules.form_management.controller.form.v1.reply;

import com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply.FormReplyRequest;
import com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply.FormReplyResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/form-reply")
@Tag(name = "Form V1", description = "QL biểu mẫu phản hồi")
public interface IFormReplyV1API {
    @PostMapping
    @Operation(
            summary = "Tạo mới biểu mẫu phản hồi",
            description = """
                    - Người dùng đã xác thực tạo mới biểu mẫu phản hồi
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    """)
    ValueResponse<FormReplyResponse> createFormReply(@RequestBody @Valid FormReplyRequest request);
}

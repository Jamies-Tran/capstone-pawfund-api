package com.paw.fund.app.modules.form_management.controller.form.v1;

import com.paw.fund.app.modules.form_management.controller.models.form.FormRequest;
import com.paw.fund.app.modules.form_management.controller.models.form.FormResponse;
import com.paw.fund.app.modules.form_management.controller.models.form.FormUpdateRequest;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/v1/api/form/{formId}")
@Tag(name = "Form V1", description = "QL form")
public interface IFormPathV1API {
    @GetMapping
    @Operation(
            summary = "Chi tiết form",
            description = """
                    - Người dùng đã xác thực xem chi tiết form
                    - [AUTHENTICATED - Người dùng đã xác thực]
                    - Note: Tìm kiếm câu hỏi trong form theo ds loại câu hỏi (Category loại câu hỏi)
                    """)
    ValueResponse<FormResponse> getFormDetail(
            @PathVariable
            @Schema(description = "ID của form")
            Long formId,

            @RequestParam(required = false, value = "questionText", defaultValue = "")
            @Schema(description = "Tìm kiếm câu hỏi trong form theo nội dung câu hỏi")
            String questionText,

            @RequestParam(required = false, value = "questionTypeCodes", defaultValue = "")
            List<String> questionTypeCodes);

    @PatchMapping
    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_SHELTER_OWNER'})")
    @Operation(
            summary = "Cập nhật form",
            description = """
                    - Quản trị viên hoặc chủ trung tâm cứu hộ cập nhật form
                    - [ADMIN | SHELTER_OWNER - Quản trị viên | Chủ trung tâm cứu trợ]
                    """)
    ValueResponse<FormResponse> updateForm(
            @PathVariable
            @Schema(description = "ID của form")
            Long formId,
            @RequestBody
            @Valid
            FormUpdateRequest formRequest);

    @DeleteMapping
    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_SHELTER_OWNER'})")
    @Operation(
            summary = "Xóa form",
            description = """
                    - Quản trị viên hoặc chủ trung tâm cứu hộ xóa form
                    - [ADMIN | SHELTER_OWNER - Quản trị viên | Chủ trung tâm cứu trợ]
                    """)
    void deleteForm(
            @PathVariable
            @Schema(description = "ID của form")
            Long formId);
}

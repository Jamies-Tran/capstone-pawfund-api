package com.paw.fund.app.modules.category_management.controller.question.type;

import com.paw.fund.app.modules.category_management.controller.question.type.models.QuestionTypeCategoryResponse;
import com.paw.fund.utils.response.ListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/public/category/question-type")
@Tag(name = "Category V1", description = "QL danh mục")
public interface IQuestionTypeCategoryV1PubAPI {
    @GetMapping
    @Operation(
            summary = "Danh mục loại câu hỏi",
            description = """
                    - Tìm kiếm danh sách danh mục loại câu hỏi
                    """)
    ListResponse<QuestionTypeCategoryResponse> getQuestionTypeList(
            @RequestParam(required = false, value = "search", defaultValue = "")
            @Schema(description = "Tìm kiếm theo tên của danh mục")
            String search);
}

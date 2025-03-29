package com.paw.fund.app.modules.form_management.controller.form.v1;

import com.paw.fund.app.modules.form_management.controller.models.form.FormResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/form/{formId}")
@Tag(name = "Form", description = "QL form")
public interface IFormPathV1API {
    @GetMapping
    ValueResponse<FormResponse> getFormDetail(@PathVariable Long formId);
}

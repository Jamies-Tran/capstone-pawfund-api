package com.paw.fund.app.modules.form_management.controller.form.v1;

import com.paw.fund.app.modules.form_management.controller.models.form.FormRequest;
import com.paw.fund.app.modules.form_management.controller.models.form.FormResponse;
import com.paw.fund.utils.response.ValueResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api/form")
@Tag(name = "Form", description = "QL form")
public interface IFormV1API {
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ValueResponse<FormResponse> createShelterRegisterForm(@RequestBody FormRequest formRequest);
}

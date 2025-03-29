package com.paw.fund.app.modules.form_management.controller.form.v1;

import com.paw.fund.app.modules.form_management.controller.models.form.FormResponse;
import com.paw.fund.app.modules.form_management.controller.models.form.IFormModelMapper;
import com.paw.fund.app.modules.form_management.domain.form.Form;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormId;
import com.paw.fund.app.modules.form_management.service.form.usecase.IFormUseCase;
import com.paw.fund.utils.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormPathV1Controller implements IFormPathV1API {
    @NonNull
    IFormUseCase useCase;

    @NonNull
    IFormModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<FormResponse> getFormDetail(Long formId) {
        Form foundForm = useCase.getFormDetail(FormId.of(formId));

        return ValueResponse.success(modelMapper.toResponse(foundForm), HttpStatus.OK, API_VERSION);
    }
}

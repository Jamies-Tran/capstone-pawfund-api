package com.paw.fund.app.modules.form_management.controller.form.v1;

import com.paw.fund.app.modules.form_management.controller.models.form.FormResponse;
import com.paw.fund.app.modules.form_management.controller.models.form.FormUpdateRequest;
import com.paw.fund.app.modules.form_management.controller.models.form.IFormModelMapper;
import com.paw.fund.app.modules.form_management.domain.form.Form;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormDetail;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormId;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormQuestionSearchCriteria;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormUpdate;
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

import java.util.List;

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
    public ValueResponse<FormResponse> getFormDetail(Long formId,
                                                     String questionText,
                                                     List<String> questionTypeCodes) {
        FormQuestionSearchCriteria questionSearchCriteria = FormQuestionSearchCriteria
                .of(questionText, questionTypeCodes);

        Form foundForm = useCase.getFormDetail(FormDetail.of(formId, questionSearchCriteria));

        return ValueResponse.success(modelMapper.toResponse(foundForm), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<FormResponse> updateForm(Long formId, FormUpdateRequest formRequest) {
        Form form = modelMapper.toDto(formRequest);
        FormUpdate formUpdate = FormUpdate.of(formId, form);
        Form updatedForm = useCase.updateForm(formUpdate);

        return ValueResponse.success(modelMapper.toResponse(updatedForm), HttpStatus.OK, API_VERSION);
    }

    @Override
    public void deleteForm(Long formId) {
        useCase.deleteForm(FormId.of(formId));
    }
}

package com.paw.fund.app.modules.form_management.controller.form.v1;

import com.paw.fund.app.modules.form_management.controller.models.form.FormRequest;
import com.paw.fund.app.modules.form_management.controller.models.form.FormResponse;
import com.paw.fund.app.modules.form_management.controller.models.form.IFormModelMapper;
import com.paw.fund.app.modules.form_management.domain.form.Form;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormFilter;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormSearchCriteria;
import com.paw.fund.app.modules.form_management.service.form.usecase.IFormUseCase;
import com.paw.fund.enums.EFormType;
import com.paw.fund.utils.request.PageRequestCustom;
import com.paw.fund.utils.response.Meta;
import com.paw.fund.utils.response.PageResponse;
import com.paw.fund.utils.response.ValueResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormV1Controller implements IFormV1API {
    @NonNull
    IFormUseCase useCase;

    @NonNull
    IFormModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<FormResponse> createShelterRegisterForm(FormRequest formRequest) {
        Form form = modelMapper.toDto(formRequest)
                .withFormTypeCode(EFormType.SHELTER_REGISTER.getCode())
                .withFormTypeName(EFormType.SHELTER_REGISTER.getName());
        Form savedForm = useCase.createForm(form);

        return ValueResponse.success(modelMapper.toResponse(savedForm), HttpStatus.CREATED, API_VERSION);
    }

    @Override
    public PageResponse<FormResponse> getFormList(String search,
                                                  List<LocalDateTime> timeRange,
                                                  List<String> formTypeCodes,
                                                  String sorter, Integer current, Integer pageSize) {
        FormSearchCriteria searchCriteria = FormSearchCriteria.of(search, formTypeCodes, timeRange);
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(current, pageSize, sorter);
        Page<FormResponse> responses = useCase.getFormList(FormFilter.of(searchCriteria, pageRequestCustom))
                .map(modelMapper::toResponse);

        return PageResponse.success(
                responses.getContent(),
                Meta.of(responses),
                HttpStatus.OK,
                API_VERSION);
    }
}

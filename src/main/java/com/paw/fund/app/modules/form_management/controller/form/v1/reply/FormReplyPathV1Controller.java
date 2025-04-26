package com.paw.fund.app.modules.form_management.controller.form.v1.reply;

import com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply.FormReplyRequest;
import com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply.FormReplyResponse;
import com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply.IFormReplyModelMapper;
import com.paw.fund.app.modules.form_management.domain.form.reply.FormReply;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormReplyId;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormReplyUpdate;
import com.paw.fund.app.modules.form_management.service.form.usecase.IFormReplyUseCase;
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
public class FormReplyPathV1Controller implements IFormReplyPathV1API {
    @NonNull
    IFormReplyUseCase useCase;

    @NonNull
    IFormReplyModelMapper modelMapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<FormReplyResponse> getFormReplyDetail(Long formResponseId) {
        FormReply formReply = useCase.getFormReplyDetail(FormReplyId.of(formResponseId));

        return ValueResponse.success(modelMapper.toResponse(formReply), HttpStatus.OK, API_VERSION);
    }

    @Override
    public ValueResponse<FormReplyResponse> updateFormReply(Long formResponseId,
                                                            FormReplyRequest formReplyRequest) {
        FormReply formReply = useCase.updateFormReply(FormReplyUpdate.of(formReplyRequest.formId(),
                modelMapper.toDto(formReplyRequest)));

        return ValueResponse.success(modelMapper.toResponse(formReply), HttpStatus.OK, API_VERSION);
    }
}

package com.paw.fund.app.modules.form_management.controller.form.v1.reply;

import com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply.FormReplyRequest;
import com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply.FormReplyResponse;
import com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply.IFormReplyModelMapper;
import com.paw.fund.app.modules.form_management.domain.form.reply.FormReply;
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
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class FormReplyV1Controller implements IFormReplyV1API {
    @NonNull
    IFormReplyUseCase useCase;

    @NonNull
    IFormReplyModelMapper mapper;

    @NonFinal
    @Value("${app.version}")
    String API_VERSION;

    @Override
    public ValueResponse<FormReplyResponse> createFormReply(FormReplyRequest request) {
        FormReply formReply = mapper.toDto(request);
        FormReply saveFormReply = useCase.createFormReply(formReply);

        return ValueResponse.success(mapper.toResponse(saveFormReply), HttpStatus.CREATED);
    }
}

package com.paw.fund.app.modules.form_management.controller.form.v1.models.form.reply;

import com.paw.fund.app.modules.form_management.domain.form.reply.FormReply;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IFormReplyModelMapper {
    FormReply toDto(FormReplyRequest request);

    FormReplyResponse toResponse(FormReply dto);
}

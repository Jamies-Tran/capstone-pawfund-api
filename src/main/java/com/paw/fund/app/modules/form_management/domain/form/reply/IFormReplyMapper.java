package com.paw.fund.app.modules.form_management.domain.form.reply;

import com.paw.fund.app.modules.form_management.repository.database.form.reply.FormReplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface IFormReplyMapper {
    FormReplyEntity toEntity(FormReply dto);

    FormReply toDto(FormReplyEntity entity);

    void update(@MappingTarget FormReplyEntity entity, FormReply dto);
}

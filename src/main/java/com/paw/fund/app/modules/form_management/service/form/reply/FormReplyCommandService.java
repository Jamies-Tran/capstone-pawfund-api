package com.paw.fund.app.modules.form_management.service.form.reply;

import com.paw.fund.app.modules.form_management.domain.form.reply.FormReply;
import com.paw.fund.app.modules.form_management.domain.form.reply.IFormReplyMapper;
import com.paw.fund.app.modules.form_management.repository.database.form.reply.FormReplyEntity;
import com.paw.fund.app.modules.form_management.repository.database.form.reply.IFormReplyRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormReplyCommandService {
    @NonNull
    IFormReplyRepository repository;

    @NonNull
    IFormReplyMapper mapper;

    public FormReply save(FormReply formReply) {
        FormReplyEntity newFormReply = mapper.toEntity(formReply);
        newFormReply.setResponseAt(LocalDateTime.now());
        FormReplyEntity saveFormReply = repository.save(newFormReply);

        return mapper.toDto(saveFormReply);
    }

    public FormReply update(Long formResponseId, FormReply formReply) {
        ValidationUtil.validateArgumentNotNull(formResponseId);
        ValidationUtil.validateNotNullPointerException(formReply);

        return repository.findById(formResponseId)
                .map(x -> {
                    mapper.update(x, formReply);
                    FormReplyEntity updatedFormReply = repository.save(x);

                    return mapper.toDto(updatedFormReply);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }
}

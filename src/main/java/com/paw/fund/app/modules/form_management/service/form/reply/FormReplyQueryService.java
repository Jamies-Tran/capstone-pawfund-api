package com.paw.fund.app.modules.form_management.service.form.reply;

import com.paw.fund.app.modules.form_management.domain.form.reply.FormReply;
import com.paw.fund.app.modules.form_management.domain.form.reply.IFormReplyMapper;
import com.paw.fund.app.modules.form_management.repository.database.form.reply.IFormReplyRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormReplyQueryService {
    @NonNull
    IFormReplyRepository repository;

    @NonNull
    IFormReplyMapper mapper;

    public Boolean existsByFormResponseId(Long formResponseId) {
        return repository.existsById(formResponseId);
    }

    public FormReply findByFormResponseId(Long formResponseId) {
        return repository.findById(formResponseId)
                .map(mapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }
}

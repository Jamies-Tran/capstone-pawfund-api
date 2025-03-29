package com.paw.fund.app.modules.form_management.service.form;

import com.paw.fund.app.modules.form_management.domain.form.Form;
import com.paw.fund.app.modules.form_management.domain.form.IFormMapper;
import com.paw.fund.app.modules.form_management.domain.question.Question;
import com.paw.fund.app.modules.form_management.repository.database.form.FormEntity;
import com.paw.fund.app.modules.form_management.repository.database.form.IFormRepository;
import com.paw.fund.app.modules.form_management.service.question.QuestionQueryService;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormQueryService {
    @NonNull
    IFormRepository repository;

    @NonNull
    IFormMapper mapper;

    public Form findById(Long formId) {
        ValidationUtil.validateArgumentNotNull(formId);
        FormEntity foundForm = repository.findById(formId)
                .orElseThrow(ResourceNotFoundException::new);

        return mapper.toDto(foundForm);
    }
}

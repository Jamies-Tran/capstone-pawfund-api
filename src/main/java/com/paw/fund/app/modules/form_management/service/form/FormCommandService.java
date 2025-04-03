package com.paw.fund.app.modules.form_management.service.form;

import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.app.modules.form_management.domain.form.Form;
import com.paw.fund.app.modules.form_management.domain.form.IFormMapper;
import com.paw.fund.app.modules.form_management.repository.database.form.FormEntity;
import com.paw.fund.app.modules.form_management.repository.database.form.IFormRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceDuplicateException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormCommandService {
    @NonNull
    IFormRepository repository;

    @NonNull
    IFormMapper mapper;

    @NonNull
    IAuditableUseCase auditableUseCase;

    public Form save(Form form) {
        ValidationUtil.validateNotNullPointerException(form);
        if(repository.existsByTitle(form.title())) {
            throw new ResourceDuplicateException("Tiêu đề form đã tồn tại");
        }
        FormEntity newForm = mapper.toEntity(form);
        FormEntity savedForm = repository.save(newForm);
        savedForm.prepareSave(auditableUseCase.createAuditableForNew());

        return mapper.toDto(savedForm);
    }

    public Form update(Long formId, Form form) {
        ValidationUtil.validateArgumentNotNull(formId);
        ValidationUtil.validateNotNullPointerException(form);
        FormEntity foundForm = repository.findById(formId).orElseThrow(ResourceNotFoundException::new);
        mapper.update(foundForm, form);
        foundForm.prepareSave(auditableUseCase.createAuditableForNew());
        FormEntity savedForm = repository.save(foundForm);

        return mapper.toDto(savedForm);
    }
}

package com.paw.fund.app.modules.form_management.service.form;

import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.app.modules.form_management.domain.form.Form;
import com.paw.fund.app.modules.form_management.domain.form.IFormMapper;
import com.paw.fund.app.modules.form_management.repository.database.form.FormEntity;
import com.paw.fund.app.modules.form_management.repository.database.form.IFormRepository;
import com.paw.fund.configuration.handler.exceptions.ResourceDuplicateException;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.enums.EDeleteStatus;
import com.paw.fund.enums.EFormStatus;
import com.paw.fund.enums.EFormType;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        validateSave(form);
        if(repository.existsByTitle(form.title())) {
            throw new ResourceDuplicateException("Tiêu đề form đã tồn tại");
        }
        FormEntity newForm = mapper.toEntity(form);
        FormEntity savedForm = repository.save(newForm);
        savedForm.prepareSave(auditableUseCase.createAuditableForNew());

        return mapper.toDto(savedForm);
    }

    private void validateSave(Form form) {
        Boolean existsByShelterRegisterForm = repository.existsByFormTypeCode(EFormType.SHELTER_REGISTER.getCode());
        if(Objects.equals(form.formTypeCode(), EFormType.SHELTER_REGISTER.getCode()) && existsByShelterRegisterForm) {
            throw new ResourceDuplicateException("Form " + EFormType.SHELTER_REGISTER.getName() + " đã tồn tại");
        }
    }

    public Form updateStatus(Long formId, EFormStatus status) {
        ValidationUtil.validateArgumentNotNull(formId);
        ValidationUtil.validateArgumentNotNull(status);

        return repository.findByStatusCodeNotDeletedAndById(formId)
                .map(x -> {
                    x.setStatusCode(status.getCode());
                    x.setStatusName(status.getName());
                    x.prepareUpdate(auditableUseCase.createAuditableForUpdate());
                    FormEntity savedForm = repository.save(x);

                    return mapper.toDto(savedForm);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Form update(Long formId, Form form) {
        ValidationUtil.validateArgumentNotNull(formId);
        ValidationUtil.validateNotNullPointerException(form);
        FormEntity foundForm = repository.findByStatusCodeNotDeletedAndById(formId)
                .orElseThrow(ResourceNotFoundException::new);
        mapper.update(foundForm, form);
        foundForm.prepareSave(auditableUseCase.createAuditableForNew());
        FormEntity savedForm = repository.save(foundForm);

        return mapper.toDto(savedForm);
    }

    public Long delete(Long formId) {
        ValidationUtil.validateArgumentNotNull(formId);
        FormEntity foundForm = repository.findByStatusCodeNotDeletedAndById(formId)
                .orElseThrow(ResourceNotFoundException::new);
        foundForm.setStatusCode(EDeleteStatus.DELETED.getCode());
        foundForm.setStatusName(EDeleteStatus.DELETED.getName());
        foundForm.prepareUpdate(auditableUseCase.createAuditableForUpdate());
        repository.save(foundForm);

        return formId;
    }
}

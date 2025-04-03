package com.paw.fund.app.modules.form_management.service.form;

import com.paw.fund.app.modules.form_management.domain.form.Form;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormId;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormUpdate;
import com.paw.fund.app.modules.form_management.domain.question.Question;
import com.paw.fund.app.modules.form_management.repository.database.form.FormEntity;
import com.paw.fund.app.modules.form_management.service.form.usecase.IFormUseCase;
import com.paw.fund.app.modules.form_management.service.question.QuestionCommandService;
import com.paw.fund.app.modules.form_management.service.question.QuestionQueryService;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormUseCaseService implements IFormUseCase {
    @NonNull
    FormCommandService commandService;

    @NonNull
    FormQueryService queryService;

    @NonNull
    QuestionCommandService questionCommandService;

    @NonNull
    QuestionQueryService questionQueryService;

    @Override
    @Transactional
    public Form createForm(Form form) {
        ValidationUtil.validateNotNullPointerException(form);
        Form savedForm = commandService.save(form);
        List<Question> savedQuestions = questionCommandService
                .saveAllWithFormId(savedForm.formId(), form.questions());

        return savedForm.withQuestions(savedQuestions);
    }

    @Override
    public Form getFormDetail(FormId formId) {
        ValidationUtil.validateNotNullPointerException(formId);
        Form form = queryService.findById(formId.value());
        List<Question> questions = questionQueryService.findAllByFormId(formId.value());

        return form.withQuestions(questions);
    }

    @Override
    @Transactional
    public Form updateForm(FormUpdate formUpdate) {
        ValidationUtil.validateNotNullPointerException(formUpdate);
        Form form = commandService.update(formUpdate.formId(), formUpdate.form());
        List<Question> updatedQuestion = questionCommandService
                .updateAllByFormId(formUpdate.formId(), formUpdate.form().questions());

        return form.withQuestions(updatedQuestion);
    }
}

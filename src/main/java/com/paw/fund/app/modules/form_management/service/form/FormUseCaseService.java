package com.paw.fund.app.modules.form_management.service.form;

import com.paw.fund.app.modules.form_management.domain.form.Form;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormId;
import com.paw.fund.app.modules.form_management.domain.question.Question;
import com.paw.fund.app.modules.form_management.repository.database.form.FormEntity;
import com.paw.fund.app.modules.form_management.service.form.usecase.IFormUseCase;
import com.paw.fund.app.modules.form_management.service.question.QuestionCommandService;
import com.paw.fund.app.modules.form_management.service.question.QuestionQueryService;
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
        Form savedForm = commandService.save(form);
        List<Question> savedQuestions = questionCommandService
                .saveAllWithFormId(savedForm.formId(), form.questions());

        return savedForm.withQuestions(savedQuestions);
    }

    @Override
    public Form getFormDetail(FormId formId) {
        Form form = queryService.findById(formId.value());
        List<Question> questions = questionQueryService.findAllByFormId(formId.value());

        return form.withQuestions(questions);
    }
}

package com.paw.fund.app.modules.form_management.service.form;

import com.paw.fund.app.modules.form_management.domain.form.Form;
import com.paw.fund.app.modules.form_management.domain.question.Question;
import com.paw.fund.app.modules.form_management.service.form.usecase.IFormUseCase;
import com.paw.fund.app.modules.form_management.service.question.QuestionCommandService;
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
    QuestionCommandService questionCommandService;

    @Override
    @Transactional
    public Form createForm(Form form) {
        Form savedForm = commandService.save(form);
        List<Question> savedQuestions = questionCommandService
                .saveAllWithFormId(savedForm.formId(), form.questions());

        return savedForm.withQuestions(savedQuestions);
    }
}

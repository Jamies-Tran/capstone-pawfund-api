package com.paw.fund.app.modules.form_management.service.form;

import com.paw.fund.app.modules.form_management.domain.form.Form;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormDetail;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormFilter;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormId;
import com.paw.fund.app.modules.form_management.domain.form.usecase.FormUpdate;
import com.paw.fund.app.modules.form_management.domain.question.Question;
import com.paw.fund.app.modules.form_management.service.form.usecase.IFormUseCase;
import com.paw.fund.app.modules.form_management.service.question.QuestionCommandService;
import com.paw.fund.app.modules.form_management.service.question.QuestionQueryService;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Form getFormDetail(FormDetail formDetail) {
        ValidationUtil.validateNotNullPointerException(formDetail);
        Form form = queryService.findById(formDetail.formId());
        List<Question> questions = questionQueryService.findAllByFormId(formDetail.formId(),
                formDetail.formQuestionSearchCriteria());

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

    @Override
    public Page<Form> getFormList(FormFilter filter) {
        return queryService
                .findAll(filter.searchCriteria(), filter.pageRequestCustom())
                .map(x -> x.withQuestionCount(questionQueryService.countByFormId(x.formId())));
    }

    @Override
    public void deleteForm(FormId formId) {
        Long deletedId = commandService.delete(formId.value());
        questionCommandService.deleteAllByFormId(deletedId);
    }
}

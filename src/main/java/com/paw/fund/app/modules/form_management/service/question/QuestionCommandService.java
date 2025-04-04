package com.paw.fund.app.modules.form_management.service.question;

import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.app.modules.form_management.domain.option.Option;
import com.paw.fund.app.modules.form_management.domain.question.IQuestionMapper;
import com.paw.fund.app.modules.form_management.domain.question.Question;
import com.paw.fund.app.modules.form_management.repository.database.form.FormEntity;
import com.paw.fund.app.modules.form_management.repository.database.option.OptionEntity;
import com.paw.fund.app.modules.form_management.repository.database.question.IQuestionRepository;
import com.paw.fund.app.modules.form_management.repository.database.question.QuestionEntity;
import com.paw.fund.app.modules.form_management.service.option.OptionCommandService;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.enums.EDeleteStatus;
import com.paw.fund.enums.EQuestionType;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionCommandService {
    @NonNull
    IQuestionRepository repository;

    @NonNull
    IQuestionMapper mapper;

    @NonNull
    IAuditableUseCase auditableUseCase;

    @NonNull
    OptionCommandService optionCommandService;

    public List<Question> saveAllWithFormId(Long formId, List<Question> questions) {
        ValidationUtil.validateArgumentNotNull(formId);
        ValidationUtil.validateArgumentListNotNull(questions);
        List<Question> savedQuestions = questions.stream()
                .map(x -> {
                    QuestionEntity newQuestion = mapper.toEntity(x.withFormId(formId));
                    newQuestion.prepareSave(auditableUseCase.createAuditableForNew());
                    if(Objects.equals(x.questionTypeCode(), EQuestionType.MULTIPLE_CHOICE.getCode())) {
                        if(CollectionUtils.isEmpty(x.options())) {
                            throw new ResourceNotValidException("Câu hỏi trắc nghiệm phải có lựa chọn trả lời");
                        }
                        QuestionEntity savedQuestion = repository.save(newQuestion);
                        List<Option> savedOptions = optionCommandService
                                .saveAllWithQuestionId(savedQuestion.getQuestionId(), x.options());
                        return mapper.toDto(savedQuestion)
                                .withOptions(savedOptions);
                    }
                    QuestionEntity savedQuestion = repository.save(newQuestion);

                    return mapper.toDto(savedQuestion);
                }).toList();

        return savedQuestions;
    }

    public List<Question> updateAllByFormId(Long formId, List<Question> questions) {
        ValidationUtil.validateArgumentNotNull(formId);
        ValidationUtil.validateArgumentListNotNull(questions);

        Map<Long, List<Option>> newOptions = new HashMap<>();

        List<QuestionEntity> foundQuestions = repository.findAllByStatusNotDeletedFormId(formId);

        List<Long> newQuestionId = questions.stream().map(Question::questionId).toList();
        List<QuestionEntity> deleteList = foundQuestions.stream()
                .filter(x -> !newQuestionId.contains(x.getQuestionId()))
                .peek(x -> {
                    x.setStatusCode(EDeleteStatus.DELETED.getCode());
                    x.setStatusName(EDeleteStatus.DELETED.getName());
                    x.prepareUpdate(auditableUseCase.createAuditableForUpdate());
                }).toList();
        optionCommandService.deleteAllByQuestionIdIn(deleteList.stream().map(QuestionEntity::getQuestionId).toList());
        repository.saveAll(deleteList);

        Map<Long, QuestionEntity> foundQuestionMap = foundQuestions.stream()
                .collect(Collectors.toMap(QuestionEntity::getQuestionId, x -> x));

        List<QuestionEntity> saveQuestionList = questions.stream()
                .map(q -> {
                    QuestionEntity newQuestion;
                    if(Objects.isNull(q.questionId())) {
                        newQuestion = mapper.toEntity(q.withFormId(formId));
                        newQuestion.prepareSave(auditableUseCase.createAuditableForNew());

                    } else {
                        newQuestion = foundQuestionMap.computeIfAbsent(q.questionId(), _ -> {
                            QuestionEntity altQuestion = mapper.toEntity(q.withFormId(formId));
                            altQuestion.setQuestionId(null);
                            return altQuestion;
                        });
                        if(Objects.nonNull(newQuestion.getQuestionId())) {
                            mapper.update(newQuestion, q);
                            newQuestion.prepareUpdate(auditableUseCase.createAuditableForUpdate());
                        }
                    }
                    QuestionEntity savedQuestion = repository.save(newQuestion);
                    if(Objects.equals(q.questionTypeCode(), EQuestionType.MULTIPLE_CHOICE.getCode())) {
                        if(CollectionUtils.isEmpty(q.options())) {
                            throw new ResourceNotValidException("Câu hỏi trắc nghiệm phải có lựa chọn trả lời");
                        }
                        newOptions.put(savedQuestion.getQuestionId(), q.options());
                    }

                    return newQuestion;
                }).filter(Objects::nonNull).toList();

        newOptions.forEach(optionCommandService::updateAllByQuestionId);

        return saveQuestionList.stream().map(mapper::toDto).toList();
    }

    public void deleteAllByFormId(Long formId) {
        List<QuestionEntity> foundQuestions = repository.findAllByStatusNotDeletedFormId(formId)
                .stream().peek(x -> {
                    x.setStatusCode(EDeleteStatus.DELETED.getCode());
                    x.setStatusName(EDeleteStatus.DELETED.getName());
                    x.prepareUpdate(auditableUseCase.createAuditableForUpdate());
                }).toList();
        List<Long> foundQuestionIds = foundQuestions.stream().map(QuestionEntity::getQuestionId).toList();
        optionCommandService.deleteAllByQuestionIdIn(foundQuestionIds);

        repository.saveAll(foundQuestions);
    }
}

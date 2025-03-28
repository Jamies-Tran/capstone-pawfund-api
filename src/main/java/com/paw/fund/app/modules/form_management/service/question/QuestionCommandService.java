package com.paw.fund.app.modules.form_management.service.question;

import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.app.modules.form_management.domain.option.Option;
import com.paw.fund.app.modules.form_management.domain.question.IQuestionMapper;
import com.paw.fund.app.modules.form_management.domain.question.Question;
import com.paw.fund.app.modules.form_management.repository.database.question.IQuestionRepository;
import com.paw.fund.app.modules.form_management.repository.database.question.QuestionEntity;
import com.paw.fund.app.modules.form_management.service.option.OptionCommandService;
import com.paw.fund.configuration.handler.exceptions.ResourceNotValidException;
import com.paw.fund.enums.EQuestionType;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
        ValidationUtil.validateArgumentListNotNull(questions);
        List<QuestionEntity> newQuestions = questions.stream()
                .map(x -> {
                    QuestionEntity entity = mapper.toEntity(x.withFormId(formId));
                    entity.prepareSave(auditableUseCase.createAuditableForNew());

                    return entity;
                })
                .toList();
        List<QuestionEntity> savedQuestions = repository.saveAll(newQuestions);
        List<Question> multipleChoiceTypeQuestions = questions.stream()
                .filter(x -> Objects.equals(x.questionTypeCode(), EQuestionType.MULTIPLE_CHOICE.getCode()))
                .toList();
        if(!CollectionUtils.isEmpty(multipleChoiceTypeQuestions)) {
            multipleChoiceTypeQuestions.stream()
                    .filter(x -> CollectionUtils.isEmpty(x.options()))
                    .findAny()
                    .ifPresentOrElse(
                            _ -> {
                                throw new ResourceNotValidException("Câu hỏi trắc nghiệm phải có lựa chọn trả lời");
                            },
                            () -> {}
                    );
            Map<Long, List<Option>> questionMap = multipleChoiceTypeQuestions.stream()
                    .collect(Collectors.toMap(Question::questionId, Question::options));
            List<Option> savedOption = questionMap.entrySet()
                    .stream()
                    .map(x -> optionCommandService.saveAllWithQuestionId(x.getKey(), x.getValue()))
                    .findAny()
                    .orElse(List.of());

            return savedQuestions.stream()
                    .map(x -> mapper.toDto(x).withOptions(savedOption))
                    .toList();
        }

        return savedQuestions.stream()
                .map(mapper::toDto)
                .toList();
    }
}

package com.paw.fund.app.modules.form_management.service.question;

import com.paw.fund.app.modules.auditable_management.service.usecase.IAuditableUseCase;
import com.paw.fund.app.modules.form_management.domain.option.Option;
import com.paw.fund.app.modules.form_management.domain.question.IQuestionMapper;
import com.paw.fund.app.modules.form_management.domain.question.Question;
import com.paw.fund.app.modules.form_management.repository.database.option.OptionEntity;
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
        ValidationUtil.validateArgumentNotNull(formId);
        ValidationUtil.validateArgumentListNotNull(questions);
        List<Question> savedQuestions = questions.stream()
                .map(x -> {
                    QuestionEntity newQuestion = mapper.toEntity(x.withFormId(formId));
                    QuestionEntity savedQuestion = repository.save(newQuestion);
                    if(Objects.equals(x.questionTypeCode(), EQuestionType.MULTIPLE_CHOICE.getCode())) {
                        if(CollectionUtils.isEmpty(x.options())) {
                            throw new ResourceNotValidException("Câu hỏi trắc nghiệm phải có lựa chọn trả lời");
                        }

                        List<Option> savedOptions = optionCommandService
                                .saveAllWithQuestionId(savedQuestion.getQuestionId(), x.options());
                        return mapper.toDto(savedQuestion)
                                .withOptions(savedOptions);
                    }

                    return mapper.toDto(savedQuestion);
                }).toList();

        return savedQuestions;
    }
}

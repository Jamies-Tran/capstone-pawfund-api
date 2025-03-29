package com.paw.fund.app.modules.form_management.service.question;

import com.paw.fund.app.modules.form_management.domain.option.Option;
import com.paw.fund.app.modules.form_management.domain.question.IQuestionMapper;
import com.paw.fund.app.modules.form_management.domain.question.Question;
import com.paw.fund.app.modules.form_management.repository.database.question.IQuestionRepository;
import com.paw.fund.app.modules.form_management.repository.database.question.QuestionEntity;
import com.paw.fund.app.modules.form_management.service.option.OptionQueryService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionQueryService {
    @NonNull
    IQuestionRepository repository;

    @NonNull
    IQuestionMapper mapper;

    @NonNull
    OptionQueryService optionQueryService;

    public List<Question> findAllByFormId(Long formId) {
        List<QuestionEntity> foundQuestions = repository.findAllByFormId(formId);
        List<Long> questionIds = foundQuestions.stream()
                .map(QuestionEntity::getQuestionId)
                .toList();
        Map<Long, List<Option>> options = optionQueryService.findAllByQuestionIdIn(questionIds)
                .stream()
                .collect(Collectors.groupingBy(Option::questionId));

        return foundQuestions.stream()
                .map(x -> mapper.toDto(x).withOptions(options.get(x.getQuestionId())))
                .toList();
    }
}

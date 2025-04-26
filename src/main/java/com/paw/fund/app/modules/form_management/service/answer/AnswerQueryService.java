package com.paw.fund.app.modules.form_management.service.answer;

import com.paw.fund.app.modules.form_management.domain.answer.Answer;
import com.paw.fund.app.modules.form_management.domain.answer.IAnswerMapper;
import com.paw.fund.app.modules.form_management.domain.answer.option.AnswerOption;
import com.paw.fund.app.modules.form_management.domain.option.Option;
import com.paw.fund.app.modules.form_management.repository.database.answer.IAnswerRepository;
import com.paw.fund.app.modules.form_management.service.answer.option.AnswerOptionQueryService;
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
public class AnswerQueryService {
    @NonNull
    IAnswerRepository repository;

    @NonNull
    IAnswerMapper mapper;

    @NonNull
    AnswerOptionQueryService answerOptionQueryService;

    public List<Answer> findAllByFormResponseIdFromDAO(Long formResponseId) {
        List<Answer> answers = repository.findAllByFormResponseIdAsDAO(formResponseId)
                .stream()
                .map(mapper::toDto)
                .toList();
        List<Long> answerIds = answers.stream()
                .map(Answer::answerId)
                .toList();
        Map<Long, List<AnswerOption>> answerOptions = answerOptionQueryService.findAllByAnswerIdIn(answerIds)
                .stream()
                .collect(Collectors.groupingBy(AnswerOption::answerId));

        return answers.stream()
                .map(x -> {
                    List<Option> options = answerOptions.computeIfAbsent(x.answerId(), _ -> List.of())
                            .stream()
                            .map(xx -> xx.option()
                                    .withAnswerOptionId(xx.answerOptionId()))
                            .toList();
                    return x.withOptions(options);
                })
                .toList();
    }
}

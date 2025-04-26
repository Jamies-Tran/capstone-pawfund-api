package com.paw.fund.app.modules.form_management.service.answer.option;

import com.paw.fund.app.modules.form_management.domain.answer.option.AnswerOption;
import com.paw.fund.app.modules.form_management.domain.answer.option.IAnswerOptionMapper;
import com.paw.fund.app.modules.form_management.domain.option.Option;
import com.paw.fund.app.modules.form_management.repository.database.answer.option.IAnswerOptionRepository;
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
public class AnswerOptionQueryService {
    @NonNull
    IAnswerOptionRepository repository;

    @NonNull
    IAnswerOptionMapper mapper;

    @NonNull
    OptionQueryService optionQueryService;

    public List<AnswerOption> findAllByAnswerIdIn(List<Long> answerIds) {
        List<AnswerOption> foundQuestionAnswer = repository.findAllByAnswerIdIn(answerIds)
                .stream()
                .map(mapper::toDto)
                .toList();
        List<Long> optionIds = foundQuestionAnswer.stream()
                .map(AnswerOption::optionId)
                .toList();
        Map<Long, Option> options = optionQueryService.findAllByIdIn(optionIds)
                .stream()
                .collect(Collectors.toMap(Option::optionId, option -> option));

        return foundQuestionAnswer.stream()
                .map(x -> x.withOption(options.get(x.optionId())))
                .toList();
    }
}

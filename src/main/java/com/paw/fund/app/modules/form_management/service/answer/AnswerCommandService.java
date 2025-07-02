package com.paw.fund.app.modules.form_management.service.answer;


import com.paw.fund.app.modules.form_management.domain.answer.Answer;
import com.paw.fund.app.modules.form_management.domain.answer.IAnswerMapper;
import com.paw.fund.app.modules.form_management.domain.answer.option.AnswerOption;
import com.paw.fund.app.modules.form_management.domain.option.Option;
import com.paw.fund.app.modules.form_management.repository.database.answer.AnswerEntity;
import com.paw.fund.app.modules.form_management.repository.database.answer.IAnswerRepository;
import com.paw.fund.app.modules.form_management.service.answer.option.AnswerOptionCommandService;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerCommandService {
    @NonNull
    IAnswerRepository repository;

    @NonNull
    IAnswerMapper mapper;

    @NonNull
    AnswerOptionCommandService answerOptionCommandService;

    public List<Answer> save(Long formResponseId, List<Answer> answers) {
        return answers.stream()
                .map(x -> {
                    AnswerEntity newAnswer = mapper.toEntity(x);
                    newAnswer.setFormResponseId(formResponseId);
                    AnswerEntity saveAnswer = repository.save(newAnswer);
                    if(!CollectionUtils.isEmpty(x.options())) {
                        List<Long> optionIds = x.options().stream()
                                .map(Option::optionId)
                                .toList();
                        List<Option> saveOptions = answerOptionCommandService
                                .saveAllWithExistOptions(saveAnswer.getAnswerId(), optionIds)
                                .stream()
                                .map(AnswerOption::option)
                                .toList();

                        return mapper.toDto(saveAnswer).withOptions(saveOptions);
                    }

                    return mapper.toDto(saveAnswer);
                })
                .toList();
    }

    public List<Answer> updateAllByFormResponseId(Long formResponseId, List<Answer> answers) {
        ValidationUtil.validateArgumentNotNull(formResponseId);
        ValidationUtil.validateArgumentListNotNull(answers);

        List<AnswerEntity> foundAnswers = repository.findAllByFormResponseId(formResponseId);
        List<Long> newAnswerIds = answers.stream().map(Answer::answerId).toList();

        List<Long> deletedIdList = foundAnswers.stream()
                .map(AnswerEntity::getAnswerId)
                .filter(x -> !newAnswerIds.contains(x))
                .toList();
        answerOptionCommandService.deleteAllByAnswerIdIn(deletedIdList);
        repository.deleteAllById(deletedIdList);

        Map<Long, AnswerEntity> foundAnswerMap = foundAnswers.stream()
                .collect(Collectors.toMap(AnswerEntity::getAnswerId, x -> x));
        return answers.stream()
                .map(x -> {
                    AnswerEntity newAnswer;
                    if(Objects.isNull(x.answerId())) {
                        newAnswer = mapper.toEntity(x.withFormResponseId(formResponseId));
                    } else {
                        newAnswer = foundAnswerMap.computeIfAbsent(x.answerId(), _ -> {
                            AnswerEntity answer = mapper.toEntity(x);
                            answer.setAnswerId(null);
                            answer.setFormResponseId(formResponseId);

                            return answer;
                        });
                    }
                    mapper.update(newAnswer, x);
                    AnswerEntity updateAnswer = repository.save(newAnswer);
                    if(!CollectionUtils.isEmpty(x.options())) {
                        List<Option> saveOptions = answerOptionCommandService
                                .updateAllByAnswerIdWithExistOptions(updateAnswer.getAnswerId(), x.options())
                                .stream()
                                .map(AnswerOption::option)
                                .toList();

                        return mapper.toDto(updateAnswer).withOptions(saveOptions);
                    }

                    return mapper.toDto(updateAnswer);
                })
                .toList();
    }

}

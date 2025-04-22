package com.paw.fund.app.modules.form_management.service.answer.option;

import com.paw.fund.app.modules.form_management.domain.answer.option.AnswerOption;
import com.paw.fund.app.modules.form_management.domain.answer.option.IAnswerOptionMapper;
import com.paw.fund.app.modules.form_management.domain.option.Option;
import com.paw.fund.app.modules.form_management.repository.database.answer.option.AnswerOptionEntity;
import com.paw.fund.app.modules.form_management.repository.database.answer.option.IAnswerOptionRepository;
import com.paw.fund.app.modules.form_management.service.option.OptionQueryService;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AnswerOptionCommandService {
    @NonNull
    IAnswerOptionRepository repository;

    @NonNull
    IAnswerOptionMapper mapper;

    @NonNull
    OptionQueryService optionQueryService;

    public void deleteAllByAnswerIdIn(List<Long> answerIds) {
        List<AnswerOptionEntity> deletedList = repository.findAllByAnswerIdIn(answerIds);

        repository.deleteAll(deletedList);
    }

    public List<AnswerOption> saveAllWithExistOptions(Long answerId, List<Long> optionIds) {
        List<Option> foundOptions = optionQueryService.findAllByIdIn(optionIds);
        Map<Long, Option> optionIdMap = foundOptions.stream()
                .collect(Collectors.toMap(Option::optionId, xx -> xx));
        List<AnswerOptionEntity> newAnswerOptions = foundOptions.stream()
                .map(x -> {
                    AnswerOption answerOption = AnswerOption.builder()
                            .answerId(answerId)
                            .optionId(x.optionId())
                            .build();
                    return mapper.toEntity(answerOption);
                })
                .toList();

        return repository.saveAll(newAnswerOptions)
                .stream()
                .map(x -> mapper
                        .toDto(x)
                        .withOption(optionIdMap.computeIfAbsent(x.getOptionId(), _ -> null))
                )
                .toList();
    }

    public List<AnswerOption> updateAllByAnswerIdWithExistOptions(Long answerId, List<Option> options) {
        ValidationUtil.validateArgumentNotNull(answerId);

        List<AnswerOptionEntity> foundAnswerOptions = repository.findAllByAnswerId(answerId);
        List<Long> newAnswerOptionIds = options.stream().map(Option::answerOptionId).toList();
        List<Long> deletedIdList = foundAnswerOptions.stream()
                .map(AnswerOptionEntity::getAnswerOptionId)
                .filter(x -> !newAnswerOptionIds.contains(x))
                .toList();
        repository.deleteAllById(deletedIdList);

        Map<Long, AnswerOptionEntity> answerOptions = foundAnswerOptions.stream()
                .collect(Collectors.toMap(AnswerOptionEntity::getAnswerOptionId, x -> x));
        List<AnswerOptionEntity> newAnswerOptions = options.stream()
                .map(x -> {
                    AnswerOptionEntity newAnswerOption;
                    AnswerOption answerOption = AnswerOption.builder()
                            .answerId(answerId)
                            .optionId(x.optionId())
                            .build();
                    if(Objects.isNull(x.answerOptionId())) {
                        newAnswerOption = mapper.toEntity(answerOption);
                    } else {
                        newAnswerOption = answerOptions
                                .computeIfAbsent(x.answerOptionId(), _ -> {
                                    AnswerOptionEntity altAnswerOption = mapper.toEntity(answerOption);
                                    altAnswerOption.setAnswerOptionId(null);

                                    return altAnswerOption;
                                });
                    }
                    mapper.update(newAnswerOption, answerOption);

                    return newAnswerOption;
                })
                .toList();
        List<Long> optionIds = newAnswerOptions.stream()
                .map(AnswerOptionEntity::getOptionId)
                .toList();
        Map<Long, Option> foundOptions = optionQueryService.findAllByIdIn(optionIds)
                .stream()
                .collect(Collectors.toMap(Option::optionId, xx -> xx));

        return repository.saveAll(newAnswerOptions)
                .stream()
                .map(x -> mapper.toDto(x).withOption(foundOptions.computeIfAbsent(x.getOptionId(), _ -> null)))
                .toList();
    }
}

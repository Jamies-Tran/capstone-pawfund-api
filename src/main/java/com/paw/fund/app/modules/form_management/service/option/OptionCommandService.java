package com.paw.fund.app.modules.form_management.service.option;

import com.paw.fund.app.modules.form_management.domain.option.IOptionMapper;
import com.paw.fund.app.modules.form_management.domain.option.Option;
import com.paw.fund.app.modules.form_management.repository.database.option.IOptionRepository;
import com.paw.fund.app.modules.form_management.repository.database.option.OptionEntity;
import com.paw.fund.enums.EDeleteStatus;
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
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OptionCommandService {
    @NonNull
    IOptionRepository repository;

    @NonNull
    IOptionMapper mapper;

    public List<Option> saveAllWithQuestionId(Long questionId, List<Option> options) {
        ValidationUtil.validateArgumentNotNull(questionId);
        ValidationUtil.validateArgumentListNotNull(options);
        List<OptionEntity> newOptions = options.stream()
                .map(x -> mapper.toEntity(x.withQuestionId(questionId)))
                .toList();
        List<OptionEntity> savedOptions = repository.saveAll(newOptions);

        return savedOptions.stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<Option> updateAllByQuestionId(Long questionId, List<Option> options) {
        ValidationUtil.validateArgumentNotNull(questionId);
        ValidationUtil.validateArgumentListNotNull(options);

        List<OptionEntity> foundOptions = repository.findAllByQuestionId(questionId);

        List<Long> newOptionId = options.stream().map(Option::optionId).toList();
        List<Long> deleteIdList = foundOptions.stream()
                .map(OptionEntity::getOptionId)
                .filter(optionId -> !newOptionId.contains(optionId))
                .toList();
        repository.deleteAllById(deleteIdList);

        Map<Long, OptionEntity> foundOptionMap = foundOptions.stream()
                .collect(Collectors.toMap(OptionEntity::getOptionId, x -> x));
        List<OptionEntity> newOptionList = options.stream()
                .map(x -> {
                    OptionEntity newOption;
                    if(Objects.isNull(x.optionId())) {
                        newOption = mapper.toEntity(x.withQuestionId(questionId));
                    } else {
                        newOption = foundOptionMap.computeIfAbsent(x.optionId(), _ -> {
                            OptionEntity altOption = mapper.toEntity(x.withQuestionId(questionId));
                            altOption.setOptionId(null);

                            return altOption;
                        });

                        mapper.update(newOption, x);
                    }

                    return newOption;
                }).toList();
        List<OptionEntity> savedOptions = repository.saveAll(newOptionList);

        return savedOptions.stream().map(mapper::toDto).toList();
    }

    public void deleteAllByQuestionIdIn(List<Long> questionIds) {
        List<OptionEntity> foundOptions = repository.findAllByQuestionIdIn(questionIds)
                .stream()
                .peek(x -> {
                    x.setStatusCode(EDeleteStatus.DELETED.getCode());
                    x.setStatusName(EDeleteStatus.DELETED.getName());
                }).toList();

        repository.saveAll(foundOptions);
    }
}

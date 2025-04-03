package com.paw.fund.app.modules.form_management.service.option;

import com.paw.fund.app.modules.form_management.domain.option.IOptionMapper;
import com.paw.fund.app.modules.form_management.domain.option.Option;
import com.paw.fund.app.modules.form_management.repository.database.option.IOptionRepository;
import com.paw.fund.app.modules.form_management.repository.database.option.OptionEntity;
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
        Map<Long, OptionEntity> foundOptionMap = foundOptions.stream()
                .collect(Collectors.toMap(OptionEntity::getOptionId, x -> x));
        List<OptionEntity> newOptionList = options.stream()
                .map(x -> {
                    OptionEntity newOption;
                    if(Objects.isNull(x.optionId())) {
                        newOption = mapper.toEntity(x.withQuestionId(questionId));
                    } else {
                        newOption = foundOptionMap.computeIfAbsent(x.optionId(), _ -> null);
                        if(Objects.nonNull(newOption)) {
                            mapper.update(newOption, x);
                        }
                    }

                    return newOption;
                }).toList();
        List<OptionEntity> savedOptions = repository.saveAll(newOptionList);

        return savedOptions.stream().map(mapper::toDto).toList();
    }
}

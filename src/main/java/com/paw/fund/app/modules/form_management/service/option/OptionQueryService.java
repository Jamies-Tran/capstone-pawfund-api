package com.paw.fund.app.modules.form_management.service.option;

import com.paw.fund.app.modules.form_management.domain.option.IOptionMapper;
import com.paw.fund.app.modules.form_management.domain.option.Option;
import com.paw.fund.app.modules.form_management.repository.database.option.IOptionRepository;
import com.paw.fund.app.modules.form_management.repository.database.option.OptionEntity;
import com.paw.fund.configuration.handler.exceptions.ResourceNotFoundException;
import com.paw.fund.utils.validation.ValidationUtil;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OptionQueryService {
    @NonNull
    IOptionRepository repository;

    @NonNull
    IOptionMapper mapper;

    public List<Option> findAllByQuestionIdIn(List<Long> questionIds) {
        List<OptionEntity> foundOptions = repository
                .findAllByStatusCodeNotDeletedAndQuestionIdIn(questionIds);

        return foundOptions.stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<Option> findAllByIdIn(List<Long> optionIds) {

        return repository.findAllByStatusCodeNotDeletedAndOptionIdIn(optionIds)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}

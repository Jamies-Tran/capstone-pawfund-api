package com.paw.fund.app.modules.category_management.service.question.type;

import com.paw.fund.app.modules.category_management.domain.QuestionTypeCategory;
import com.paw.fund.enums.EQuestionType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionTypeCategoryQueryService {
    public List<QuestionTypeCategory> findAll(String search) {
        return Stream.of(EQuestionType.values())
                .map(QuestionTypeCategory::of)
                .filter(x -> x.name().toLowerCase().contains(search.toLowerCase()))
                .toList();
    }
}

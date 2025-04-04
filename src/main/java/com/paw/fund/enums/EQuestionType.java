package com.paw.fund.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum EQuestionType {
    MULTIPLE_CHOICE("MULTIPLE_CHOICE", "Câu hỏi trắc nghiệm"),
    ESSAY("ESSAY", "Câu hỏi tự luận"),;

    String code;
    String name;
}

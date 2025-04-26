package com.paw.fund.app.modules.form_management.repository.database.answer;

public interface AnswerDAO {
    Long getAnswerId();

    Long getFormResponseId();

    Long getQuestionId();

    String getQuestionText();

    String getAnswerText();
}

package com.paw.fund.app.modules.form_management.repository.database.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnswerRepository extends JpaRepository<AnswerEntity, Long> {
    @Query("""
        SELECT 
            a.answerId AS answerId,
            a.formResponseId AS formResponseId,
            a.questionId AS questionId,
            q.questionText AS questionText,
            a.answerText AS answerText
        FROM AnswerEntity a
        INNER JOIN QuestionEntity q ON a.questionId = q.questionId
        WHERE a.formResponseId = :formResponseId
    """)
    List<AnswerDAO> findAllByFormResponseIdAsDAO(Long formResponseId);

    List<AnswerEntity> findAllByFormResponseId(Long formResponseId);
}

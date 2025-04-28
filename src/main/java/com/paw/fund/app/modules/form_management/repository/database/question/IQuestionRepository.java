package com.paw.fund.app.modules.form_management.repository.database.question;

import com.paw.fund.app.modules.form_management.domain.form.usecase.FormQuestionSearchCriteria;
import com.paw.fund.enums.EDeleteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionRepository extends JpaRepository<QuestionEntity, Long> {
    @Query("""
        SELECT q
        FROM QuestionEntity q
        WHERE (q.formId = :formId)
            AND (:#{#searchCriteria.isQuestionTextEmptyOrNull()} = TRUE
                OR q.questionText ILIKE %:#{#searchCriteria.questionText()}%)
            AND (:#{#searchCriteria.isQuestionTypeCodesEmptyOrNull()} = TRUE
                OR q.questionTypeCode IN :#{#searchCriteria.questionTypeCodes()})
    """)
    List<QuestionEntity> findAllByFormIdAndQuestionTextOrQuestionTypeCodeIn(
            Long formId,
            FormQuestionSearchCriteria searchCriteria);

    @Query("""
        SELECT q
        FROM QuestionEntity q
        WHERE q.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND q.formId = :formId
    """)
    List<QuestionEntity> findAllByStatusNotDeletedFormId(Long formId);

    Integer countByFormId(Long formId);
}

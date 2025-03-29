package com.paw.fund.app.modules.form_management.repository.database.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findAllByFormId(Long formId);
}

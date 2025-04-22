package com.paw.fund.app.modules.form_management.repository.database.answer.option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAnswerOptionRepository extends JpaRepository<AnswerOptionEntity, Long> {

    List<AnswerOptionEntity> findAllByAnswerIdIn(List<Long> answerIds);

    List<AnswerOptionEntity> findAllByAnswerId(Long answerId);
}

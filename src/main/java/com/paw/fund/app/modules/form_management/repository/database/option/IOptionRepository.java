package com.paw.fund.app.modules.form_management.repository.database.option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOptionRepository extends JpaRepository<OptionEntity, Long> {
    List<OptionEntity> findAllByQuestionId(Long questionId);

    @Query("""
        SELECT o
        FROM OptionEntity o
        WHERE o.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND o.questionId IN :#{#questionIds}
    """)
    List<OptionEntity> findAllByStatusCodeNotDeletedAndQuestionIdIn(List<Long> questionIds);

    List<OptionEntity> findAllByQuestionIdIn(List<Long> questionIds);
}

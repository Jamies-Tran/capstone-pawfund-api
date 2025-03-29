package com.paw.fund.app.modules.form_management.repository.database.option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOptionRepository extends JpaRepository<OptionEntity, Long> {
    List<OptionEntity> findAllByQuestionIdIn(List<Long> questionIds);
}

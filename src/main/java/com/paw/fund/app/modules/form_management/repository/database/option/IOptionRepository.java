package com.paw.fund.app.modules.form_management.repository.database.option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOptionRepository extends JpaRepository<OptionEntity, Long> {
}

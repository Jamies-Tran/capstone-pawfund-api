package com.paw.fund.app.modules.form_management.repository.database.form;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormRepository extends JpaRepository<FormEntity, Long> {
    Boolean existsByTitle(String title);
}

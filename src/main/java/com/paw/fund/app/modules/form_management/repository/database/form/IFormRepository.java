package com.paw.fund.app.modules.form_management.repository.database.form;

import com.paw.fund.app.modules.form_management.domain.form.usecase.FormSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFormRepository extends JpaRepository<FormEntity, Long> {
    Boolean existsByTitle(String title);

    @Query("""
        SELECT f
        FROM FormEntity f
        WHERE (f.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()})
            AND (f.createdAt BETWEEN :#{#searchCriteria.timeRange().get(0)} AND :#{#searchCriteria.timeRange().get(1)})
            AND (:#{#searchCriteria.isSearchEmptyOrNull()} = TRUE
                OR f.title ILIKE %:#{#searchCriteria.search()}%)
            AND (:#{#searchCriteria.isFormTypeCodeEmptyOrNull()} = TRUE
                OR f.formTypeCode IN :#{#searchCriteria.formTypeCodes()})
    """)
    Page<FormEntity> findAll(FormSearchCriteria searchCriteria, Pageable pageable);

    @Query("""
        SELECT f
        FROM FormEntity f
        WHERE f.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND f.formId = :formId
    """)
    Optional<FormEntity> findByStatusCodeNotDeletedAndById(Long formId);

    Boolean existsByFormTypeCode(String formTypeCode);
}

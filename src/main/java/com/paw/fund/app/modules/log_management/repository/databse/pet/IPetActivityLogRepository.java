package com.paw.fund.app.modules.log_management.repository.databse.pet;

import com.paw.fund.app.modules.log_management.domain.pet.usecase.PetActivityLogSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPetActivityLogRepository extends JpaRepository<PetActivityLogEntity, Long> {
    @Query("""
        SELECT pal
        FROM PetActivityLogEntity pal
        WHERE (pal.petId = :#{#searchCriteria.petId()})
            AND (pal.createdAt BETWEEN :#{#searchCriteria.timeRange().get(0)} AND :#{#searchCriteria.timeRange().get(1)})
            AND (:#{#searchCriteria.isAccountSearchNullOrEmpty()} = TRUE
                OR pal.createdByName ILIKE %:#{#searchCriteria.accountSearch()}%)
            AND (:#{#searchCriteria.isDescriptionSearchNullOrEmpty()} = TRUE
                OR pal.description ILIKE %:#{#searchCriteria.descriptionSearch()}%)
            AND (:#{#searchCriteria.isActionCodesNullOrEmpty()} = TRUE
                OR pal.actionCode IN :#{#searchCriteria.actionCodes()})
    """)
    Page<PetActivityLogEntity> findAll(PetActivityLogSearchCriteria searchCriteria, Pageable pageable);
}

package com.paw.fund.app.modules.pet_management.repository.database.health.record;

import com.paw.fund.app.modules.pet_management.domain.health.record.usecase.PetHealthRecordSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPetHealthRecordRepository extends JpaRepository<PetHealthRecordEntity, Long> {

    @Query("""
        SELECT phr
        FROM PetHealthRecordEntity phr
        WHERE (phr.petId = :#{#searchCriteria.petId()})
            AND (phr.createdAt BETWEEN :#{#searchCriteria.timeRange().get(0)}
                AND :#{#searchCriteria.timeRange().get(1)})
            AND (:#{#searchCriteria.isDiagnosisSearchNullOrEmpty()} = TRUE
                OR phr.diagnosis ILIKE %:#{#searchCriteria.diagnosisSearch()}%)
            AND (:#{#searchCriteria.isTreatmentSearchNullOrEmpty()} = TRUE
                OR phr.treatment ILIKE %:#{#searchCriteria.treatmentSearch()}%)
            AND (:#{#searchCriteria.isCheckupDateTimeRangeNullOrEmpty()} = TRUE
                OR phr.checkupDate BETWEEN :#{#searchCriteria.getCheckupDateTimeRangeMin()}
                    AND :#{#searchCriteria.getCheckupDateTimeRangeMax()})
            AND (:#{#searchCriteria.isHealthStatusCodesNullOrEmpty()} = TRUE
                OR phr.healthStatusCode IN :#{#searchCriteria.healthStatusCodes()})
    """)
    Page<PetHealthRecordEntity> findAll(PetHealthRecordSearchCriteria searchCriteria, Pageable pageable);
}

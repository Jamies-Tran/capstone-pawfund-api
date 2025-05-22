package com.paw.fund.app.modules.shelter_assignment_management.repository.database;

import com.paw.fund.app.modules.shelter_assignment_management.domain.usecase.ShelterAssignmentSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IShelterAssignmentRepository extends JpaRepository<ShelterAssignmentEntity, Long> {
    @Query("""
        SELECT sa
        FROM ShelterAssignmentEntity sa
        WHERE (sa.createdAt BETWEEN :#{#searchCriteria.timeRange().get(0)} AND :#{#searchCriteria.timeRange().get(1)})
            AND (:#{#searchCriteria.isShelterIdNullOrEmpty()} = TRUE
                OR sa.shelterId = :#{#searchCriteria.shelterId()})
            AND (:#{#searchCriteria.isPetIntakeRegistrationIdNullOrEmpty()} = TRUE
                OR sa.petIntakeRegistrationId = :#{#searchCriteria.petIntakeRegistrationId()})
            AND (:#{#searchCriteria.isStatusCodesNullOrEmpty()} = TRUE
                OR sa.statusCode IN :#{#searchCriteria.statusCodes()})
    """)
    Page<ShelterAssignmentEntity> findAll(ShelterAssignmentSearchCriteria searchCriteria, Pageable pageable);
}

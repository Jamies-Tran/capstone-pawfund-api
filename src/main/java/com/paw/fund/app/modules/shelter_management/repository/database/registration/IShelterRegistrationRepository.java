package com.paw.fund.app.modules.shelter_management.repository.database.registration;

import com.paw.fund.app.modules.shelter_management.domain.usecase.registration.ShelterRegistrationSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IShelterRegistrationRepository extends JpaRepository<ShelterRegistrationEntity, Long> {

    @Query("""
        SELECT sr
        FROM ShelterRegistrationEntity sr
        WHERE (sr.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()})
            AND (:#{#searchCriteria.isRequestAtTimeRangeNullOrEmpty()} = TRUE
                OR sr.requestAt BETWEEN :#{#searchCriteria.requestAtFrom()} AND :#{#searchCriteria.requestAtTo()})
            AND (:#{#searchCriteria.isReceivedAtTimeRangeNullOrEmpty()} = TRUE
                OR sr.receivedAt BETWEEN :#{#searchCriteria.receivedAtFrom()} AND :#{#searchCriteria.receivedAtTo()})
            AND (:#{#searchCriteria.isApprovedAtTimeRangeNullOrEmpty()} = TRUE
                OR sr.approvedAt BETWEEN :#{#searchCriteria.approvedAtFrom()} AND :#{#searchCriteria.approvedAtTo()})
            AND (:#{#searchCriteria.isRejectedAtTimeRangeNullOrEmpty()} = TRUE
                OR sr.rejectedAt BETWEEN :#{#searchCriteria.rejectedAtFrom()} AND :#{#searchCriteria.rejectedAtTo()})
            AND (:#{#searchCriteria.isStatusCodesNullOrEmpty()} = TRUE
                OR sr.statusCode IN :#{#searchCriteria.statusCodes()})
    """)
    Page<ShelterRegistrationEntity> findAll(ShelterRegistrationSearchCriteria searchCriteria,
                                            Pageable pageable);

    Optional<ShelterRegistrationEntity> findByShelterRegistrationIdAndStatusCode(Long shelterRequestId, String statusCode);

    Boolean existsByAccountIdAndStatusCodeIn(Long accountId, List<String> statusCodes);

    @Query("""
        SELECT COUNT(sr) > 0
        FROM ShelterRegistrationEntity sr
        WHERE sr.shelterId = :shelterId
            AND sr.statusCode = :#{T(com.paw.fund.enums.EShelterRegistrationStatus).APPROVED.getCode()}
    """)
    Boolean existsByApprovedShelterRegistrationByShelterId(Long shelterId);

    Optional<ShelterRegistrationEntity> findByAccountId(Long accountId);
}

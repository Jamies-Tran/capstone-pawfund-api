package com.paw.fund.app.modules.shelter_management.repository.database;

import com.paw.fund.app.modules.shelter_management.domain.usecase.ShelterSearchCriteria;
import com.paw.fund.app.modules.shelter_management.repository.database.dao.ShelterDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IShelterRepository extends JpaRepository<ShelterEntity, Long> {
    @Query("""
        SELECT COUNT(s) > 0
        FROM ShelterEntity s
        WHERE s.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND s.hotline = :hotline
    """)
    Boolean existsByHotline(String hotline);

    @Query("""
        SELECT COUNT(s) > 0
        FROM ShelterEntity s
        WHERE s.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND s.email = :email
    """)
    Boolean existsByEmail(String email);

    @Query("""
        SELECT s
        FROM ShelterEntity s
        INNER JOIN ShelterRegistrationEntity sr ON s.shelterId = sr.shelterId
        INNER JOIN AccountEntity a ON sr.accountId = a.accountId
        WHERE sr.accountId = :accountId
        ORDER BY s.shelterId DESC
        LIMIT 1
    """)
    Optional<ShelterEntity> findByAccountId(Long accountId);

    @Query("""
        SELECT s
        FROM ShelterEntity s
        WHERE (s.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            OR s.statusCode != :#{T(com.paw.fund.enums.EShelterStatus).DRAFT.getCode()})
        AND (:#{#searchCriteria.isStatusCodesNullOrEmpty()} = TRUE
            OR s.statusCode IN :#{#searchCriteria.statusCodes()})
        AND (:#{#searchCriteria.isSearchNullOrEmpty()} = TRUE
            OR (s.shelterName ILIKE %:#{#searchCriteria.search()}%
                OR s.shelterCode ILIKE %:#{#searchCriteria.search()}%))
        AND (s.createdAt BETWEEN :#{#searchCriteria.timeRange.get(0)} AND :#{#searchCriteria.timeRange.get(1)})
    """)
    Page<ShelterEntity> findAll(ShelterSearchCriteria searchCriteria, Pageable pageable);

    @Query(
            value = """
                SELECT 
                    s.shelter_id AS shelterId,
                    (
                        2 * 6371 * ASIN(SQRT(
                             POWER(SIN(RADIANS(s.latitude - :#{#searchCriteria.latitude()}) / 2), 2) +
                             COS(RADIANS(:#{#searchCriteria.latitude()})) *
                             COS(RADIANS(s.latitude)) *
                             POWER(SIN(RADIANS(s.longitude - :#{#searchCriteria.longitude()}) / 2), 2)
                         ))
                    ) AS distance
                FROM shelters s
                WHERE (s.status_code NOT IN (:#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}, :#{T(com.paw.fund.enums.EShelterStatus).DRAFT.getCode()}))
                  AND (:#{#searchCriteria.isStatusCodesNullOrEmpty()} = TRUE
                           OR s.status_code IN :#{#searchCriteria.statusCodes()})
                  AND (:#{#searchCriteria.isSearchNullOrEmpty()} = TRUE
                           OR (LOWER(s.shelter_name) LIKE '%:#{#searchCriteria.search().toLowerCase()}%'
                                   OR LOWER(s.shelter_code) LIKE '%:#{#searchCriteria.search().toLowerCase()}%'))
                  AND (s.created_at BETWEEN :#{#searchCriteria.timeRange.get(0)} AND :#{#searchCriteria.timeRange.get(1)})
                  AND (:#{#searchCriteria.isRadiusNullOrEmpty()} = TRUE
                      OR (
                        2 * 6371 * ASIN(SQRT(
                             POWER(SIN(RADIANS(s.latitude - :#{#searchCriteria.latitude()}) / 2), 2) +
                             COS(RADIANS(:#{#searchCriteria.latitude()})) *
                             COS(RADIANS(s.latitude)) *
                             POWER(SIN(RADIANS(s.longitude - :#{#searchCriteria.longitude()}) / 2), 2)
                         ))
                    ) <= :#{#searchCriteria.radius()})
                ORDER BY distance ASC 
            """,
            countQuery = """
                SELECT COUNT(*) FROM shelters;
            """,
            nativeQuery = true
    )
    Page<ShelterDAO> findAllDistance(@Param("searchCriteria") ShelterSearchCriteria searchCriteria, Pageable pageable);

    @Query("""
        SELECT COUNT(p) >= s.maximumPetCapacity
        FROM ShelterEntity s
        LEFT JOIN PetEntity p ON s.shelterId = p.shelterId
        WHERE s.shelterId = :shelterId
    """)
    Boolean existsExceedMaximumPetByShelterId(Long shelterId);
}

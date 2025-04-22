package com.paw.fund.app.modules.shelter_management.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
        ORDER BY s.shelterId DESC
        LIMIT 1
    """)
    Optional<ShelterEntity> findByAccountId(Long accountId);
}

package com.paw.fund.app.modules.license_management.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILicenseRepository extends JpaRepository<LicenseEntity, Long> {
    @Query("""
        SELECT l
        FROM LicenseEntity l
        WHERE l.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()}
            AND l.licenseId = :licenseId
    """)
    Optional<LicenseEntity> findByStatusCodeNotDeletedAndLicenseId(Long licenseId);
}

package com.paw.fund.app.modules.license_management.repository.database;

import com.paw.fund.app.modules.license_management.domain.usecase.LicenseSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("""
        SELECT l
        FROM LicenseEntity l
        WHERE (l.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()})
            AND (l.createdAt BETWEEN :#{#searchCriteria.timeRange().get(0)}
                AND :#{#searchCriteria.timeRange().get(1)})
            AND (:#{#searchCriteria.isLicenseTypeCodesEmptyOrNull()} = TRUE
                OR l.licenseTypeCode IN :#{#searchCriteria.licenseTypeCodes()})
            AND (:#{#searchCriteria.isStatusCodesEmptyOrNull()} = TRUE
                OR l.statusCode IN :#{#searchCriteria.statusCodes()})
    """)
    Page<LicenseEntity> findAll(LicenseSearchCriteria searchCriteria, Pageable pageable);
}

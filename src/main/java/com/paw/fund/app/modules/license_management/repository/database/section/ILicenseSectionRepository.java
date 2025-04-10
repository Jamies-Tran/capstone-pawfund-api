package com.paw.fund.app.modules.license_management.repository.database.section;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILicenseSectionRepository extends JpaRepository<LicenseSectionEntity, Long> {

    List<LicenseSectionEntity> findAllByLicenseId(Long licenseId);
}

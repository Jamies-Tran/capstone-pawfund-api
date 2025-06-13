package com.paw.fund.app.modules.adopt_registration_management.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdoptRegistrationRepository extends JpaRepository<AdoptRegistrationEntity, Long> {
}

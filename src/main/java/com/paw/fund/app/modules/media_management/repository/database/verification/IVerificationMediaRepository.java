package com.paw.fund.app.modules.media_management.repository.database.verification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVerificationMediaRepository extends JpaRepository<VerificationMediaEntity, Long> {
    List<VerificationMediaEntity> findAllByPetIntakeRegistrationId(Long petIntakeRegistrationId);
}

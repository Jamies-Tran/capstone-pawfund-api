package com.paw.fund.app.modules.verification_code_management.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVerificationCodeRepository extends JpaRepository<VerificationCodeEntity, Long> {
    Optional<VerificationCodeEntity> findByCodeAndAccountIdAndTypeCode(String code, Long accountId, String typeCode);
}

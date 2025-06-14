package com.paw.fund.app.modules.verification_management.repository.database;

import com.paw.fund.app.modules.verification_management.domain.usecase.data.transfer.AccountIdAndName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVerificationRepository extends JpaRepository<VerificationEntity, Long> {
    Optional<VerificationEntity> findByCodeAndAccountIdAndTypeCode(String code, Long accountId, String typeCode);

    @Query("""
        SELECT new com.paw.fund.app.modules.verification_management.domain.usecase.data.transfer.AccountIdAndName(a.accountId, CONCAT(a.firstName, ' ', a.lastName) )
        FROM AccountEntity a
        WHERE a.email = :email
    """)
    Optional<AccountIdAndName> findAccountIdByEmail(String email);

    void deleteByCode(String code);
}

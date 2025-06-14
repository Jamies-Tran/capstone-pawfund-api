package com.paw.fund.app.modules.account_management.repository.database.account;

import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountId;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountIdAndThumbnail;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountNewEmail;
import com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Boolean existsByIdentification(String identification);

    @Query("""
        SELECT a
        FROM AccountEntity a
        LEFT JOIN AccountRoleEntity ar ON a.accountId = ar.accountId
        LEFT JOIN RoleEntity r ON r.roleId = ar.roleId
        WHERE (a.statusCode != :#{T(com.paw.fund.enums.EDeleteStatus).DELETED.getCode()})
            AND (a.createdAt BETWEEN :#{#searchCriteria.timeRange().get(0)} AND :#{#searchCriteria.timeRange().get(1)})
            AND (:#{#searchCriteria.isStatusCodesEmptyOrNull()} = TRUE
                OR a.statusCode IN :#{#searchCriteria.statusCodes()})
            AND (:#{#searchCriteria.isDayOfBirthEmptyOrNull()} = TRUE 
                OR a.dateOfBirth BETWEEN :#{#searchCriteria.dayOfBirthFrom()} AND :#{#searchCriteria.dayOfBirthTo()})
            AND (:#{#searchCriteria.isGenderCodesEmptyOrNull()} = TRUE
                OR a.genderCode IN :#{#searchCriteria.genderCodes()})
            AND (:#{#searchCriteria.isRoleCodesEmptyOrNull()} = TRUE
                OR r.roleCode IN :#{#searchCriteria.roleCodes()})
            AND (:#{#searchCriteria.isSearchEmptyOrNull()} = TRUE
                OR (CONCAT(a.firstName, ' ', a.lastName) ILIKE %:#{#searchCriteria.search()}%
                    OR a.phone ILIKE %:#{#searchCriteria.search()}%
                    OR a.email ILIKE %:#{#searchCriteria.search()}%
                    OR a.address ILIKE %:#{#searchCriteria.search()}%
                    OR a.identification ILIKE %:#{#searchCriteria.search()}%))
    """)
    Page<AccountEntity> findAll(AccountSearchCriteria searchCriteria, Pageable pageable);

    @Query("""
        SELECT COUNT(a) > 0
        FROM AccountEntity a
        INNER JOIN AccountRoleEntity ar ON a.accountId = ar.accountId
        INNER JOIN ShelterEntity se ON se.accountRoleId = ar.accountRoleId
        WHERE a.accountId = :accountId
    """)
    Boolean existsAnyShelterByAccountId(Long accountId);

    @Query("""
        SELECT COUNT(a) > 0
        FROM AccountEntity a
        INNER JOIN ShelterRegistrationEntity sr ON a.accountId = sr.accountId
        WHERE a.accountId = :accountId
            AND sr.statusCode != :statusCode
    """)
    Boolean existsByAnyShelterRegistrationByIdAndStatusCodeNot(Long accountId, String statusCode);

    @Query("""
        SELECT a
        FROM AccountEntity a
        INNER JOIN VerificationEntity v ON a.accountId = v.accountId
        WHERE v.code = :verificationCode
            AND v.typeCode = :verificationTypeCode 
            AND v.expiredAt > CURRENT_TIMESTAMP 
    """)
    Optional<AccountEntity> findByVerificationCodeAndVerifyTypeCode(String verificationCode, String verificationTypeCode);

    @Query("""
        SELECT v.newEmail
        FROM AccountEntity a
        INNER JOIN VerificationEntity v ON a.accountId = v.accountId
        WHERE a.accountId = :accountId
            AND (v.code = :verificationCode AND v.expiredAt > CURRENT_TIMESTAMP)
    """)
    Optional<String> findNewEmailByAccountIdAndVerificationCode(Long accountId, String verificationCode);

    @Query("""
        SELECT new com.paw.fund.app.modules.account_management.domain.account.usecase.data.transfer.AccountIdAndThumbnail(a.accountId, cm.url)
        FROM AccountEntity a
        LEFT JOIN CommonMediaEntity cm ON a.accountId = cm.accountId
        WHERE a.accountId IN :accountIds
            AND cm.isThumbnail = TRUE
    """)
    List<AccountIdAndThumbnail> findAccountIdAndThumbnailByAccountIdIn(List<Long> accountIds);
}

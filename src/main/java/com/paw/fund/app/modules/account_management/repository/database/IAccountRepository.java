package com.paw.fund.app.modules.account_management.repository.database;

import com.paw.fund.app.modules.account_management.domain.usecase.AccountSearchCriteria;
import com.paw.fund.enums.EAccountStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}

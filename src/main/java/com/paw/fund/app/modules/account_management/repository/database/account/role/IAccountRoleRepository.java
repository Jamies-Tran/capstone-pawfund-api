package com.paw.fund.app.modules.account_management.repository.database.account.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRoleRepository extends JpaRepository<AccountRoleEntity, Long> {
    List<AccountRoleEntity> findAllByAccountId(Long accountId);

    Optional<AccountRoleEntity> findByAccountIdAndRoleId(Long accountId, Long roleId);

    @Query("""
        SELECT
            ar.shelterId AS shelterId,
            COUNT(ar.accountId) AS totalStaff
        FROM AccountRoleEntity ar
        INNER JOIN AccountEntity a ON ar.accountId = a.accountId
        WHERE a.statusCode = :#{T(com.paw.fund.enums.EAccountStatus).ACTIVE.getCode()}
            AND ar.shelterId IN :shelterIds
        GROUP BY ar.shelterId
    """)
    List<AccountRoleSummarizeInfoDAO> findAllAccountRoleSummarizeInfoByShelterIdIn(List<Long> shelterIds);
}

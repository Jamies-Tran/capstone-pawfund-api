package com.paw.fund.app.modules.role_management.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleCode(String roleCode);

    List<RoleEntity> findAllByRoleCodeIn(List<String> roleCodes);

    @Query("""
        SELECT r
        FROM RoleEntity r
        INNER JOIN AccountRoleEntity ar ON r.roleId = ar.roleId
        INNER JOIN AccountEntity a ON ar.accountId = a.accountId
        WHERE a.accountId = :accountId
    """)
    List<RoleEntity> findAllByAccountId(Long accountId);

}

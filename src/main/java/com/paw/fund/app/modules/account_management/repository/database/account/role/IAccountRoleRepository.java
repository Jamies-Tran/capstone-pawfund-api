package com.paw.fund.app.modules.account_management.repository.database.account.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRoleRepository extends JpaRepository<AccountRoleEntity, Long> {
    List<AccountRoleEntity> findAllByAccountId(Long accountId);

    Boolean existsByRoleIdAndAccountId(Long roleId, Long accountId);

    Optional<AccountRoleEntity> findByAccountIdAndRoleId(Long accountId, Long roleId);
}

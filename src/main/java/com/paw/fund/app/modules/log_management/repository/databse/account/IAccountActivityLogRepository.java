package com.paw.fund.app.modules.log_management.repository.databse.account;

import com.paw.fund.app.modules.log_management.domain.account.usecase.AccountActivityLogSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountActivityLogRepository extends JpaRepository<AccountActivityLogEntity, Long> {
    @Query("""
        SELECT aal
        FROM AccountActivityLogEntity aal
        WHERE aal.loggedAt BETWEEN :#{#searchCriteria.timeRange().get(0)} AND :#{#searchCriteria.timeRange().get(1)}
        AND (:#{#searchCriteria.isAccountIdEmpty()} = TRUE
            OR aal.accountId = :#{#searchCriteria.accountId()})
        AND (:#{#searchCriteria.isSearchEmpty()} = TRUE
            OR aal.actionName ILIKE :#{#searchCriteria.search()})
        AND (:#{#searchCriteria.isActionCodesEmpty()} = TRUE
            OR aal.actionCode IN :#{#searchCriteria.actionCodes()})
    """)
    Page<AccountActivityLogEntity> findAll(AccountActivityLogSearchCriteria searchCriteria, Pageable pageable);
}

package com.paw.fund.app.modules.login_info_management.repository.database;

import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.Account;
import com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.CurrentAccountLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILoginInfoRepository extends JpaRepository<LoginInfoEntity, Long> {
    Optional<LoginInfoEntity> findByAccountId(Long accountId);

    Optional<LoginInfoEntity> findByAccountEmail(String accountEmail);

    Optional<LoginInfoEntity> findByRefreshToken(String refreshToken);

    @Query("""
        SELECT new com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.CurrentAccountLogin(
            l.accountId,
            l.accountEmail,
            a.phone,
            CONCAT(a.firstName, ' ', a.lastName),
            l.latitude,
            l.longitude,
            null
        )
        FROM LoginInfoEntity l
        INNER JOIN AccountEntity a ON l.accountId = a.accountId
        WHERE l.accountEmail = :email
            AND l.statusCode = :#{T(com.paw.fund.enums.ELoginStatus).LOGIN.getCode()}
            AND l.accessExpiredAt > CURRENT_TIMESTAMP
        ORDER BY l.createdAt DESC
    """)
    List<CurrentAccountLogin> findCurrentAccountLoginByEmail(String email);

    @Query("""
        SELECT new com.paw.fund.app.modules.login_info_management.domain.usecase.data.transfer.Account(
            a.accountId,
            a.email,
            a.password,
            a.firstName,
            a.lastName,
            null,
            null,
            a.statusCode,
            a.statusName,
            null
        )
        FROM AccountEntity a
        WHERE a.email = :email
    """)
    Optional<Account> findAccountByEmail(String email);

    Optional<LoginInfoEntity> findFirstByAccountEmailOrderByCreatedAtDesc(String email);
}
package com.paw.fund.app.modules.session_management.repository.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISessionRepository extends JpaRepository<SessionEntity, Long> {
    Optional<SessionEntity> findByAccountId(Long accountId);

    Optional<SessionEntity> findByRefreshToken(String refreshToken);
}

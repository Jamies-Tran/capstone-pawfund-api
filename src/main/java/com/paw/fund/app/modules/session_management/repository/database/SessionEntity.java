package com.paw.fund.app.modules.session_management.repository.database;

import com.paw.fund.app.modules.auditable_management.repository.database.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sessions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SessionEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long sessionId;

    @Column
    Long accountId;

    @Column
    String refreshToken;

    @Column(name = "expired_at")
    LocalDateTime refreshExpiredAt;
}

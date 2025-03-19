package com.paw.fund.app.modules.log_management.repository.databse.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account_activity_logs")
public class AccountActivityLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountActivityLogId;

    @Column
    Long accountId;

    @Column
    String actionCode;

    @Column
    String actionName;

    @Column
    LocalDateTime loggedAt;

    @PrePersist
    public void prePersist() {
        if(Objects.isNull(loggedAt)) {
            loggedAt = LocalDateTime.now();
        }
    }
}

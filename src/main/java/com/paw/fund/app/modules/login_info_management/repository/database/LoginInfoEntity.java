package com.paw.fund.app.modules.login_info_management.repository.database;

import com.paw.fund.common.context.auditor.repository.database.AuditableEntity;
import com.paw.fund.enums.ELoginStatus;
import com.paw.fund.utils.StringUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "login_info")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginInfoEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long loginInfoId;

    @Column
    Long accountId;

    @Column
    String accountEmail;

    @Column
    String refreshToken;

    @Column
    LocalDateTime refreshExpiredAt;

    @Column
    LocalDateTime accessExpiredAt;

    @Column
    BigDecimal longitude;

    @Column
    BigDecimal latitude;

    @Column
    String statusCode;

    @Column
    String statusName;
}

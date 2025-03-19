package com.paw.fund.app.modules.account_management.repository.database;

import com.paw.fund.app.modules.auditable_management.repository.database.AuditableEntity;
import com.paw.fund.enums.EAccountStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long accountId;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    String identification;

    @Column
    String email;

    @Column
    String password;

    @Column
    String phone;

    @Column
    String address;

    @Column
    LocalDate dateOfBirth;

    @Column
    String genderCode;

    @Column
    String genderName;

    @Column
    String statusCode;

    @Column
    String statusName;

    @PrePersist
    public void prePersist() {
        if(Objects.isNull(statusCode)) {
            statusCode = EAccountStatus.INACTIVE.getCode();
            statusName = EAccountStatus.INACTIVE.getName();
        }
    }
}

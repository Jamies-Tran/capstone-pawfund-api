package com.paw.fund.app.modules.license_management.repository.database;

import com.paw.fund.app.modules.auditable_management.repository.database.AuditableEntity;
import com.paw.fund.enums.ELicenseStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "licenses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LicenseEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long licenseId;

    @Column
    String description;

    @Column
    String licenseTypeCode;

    @Column
    String licenseTypeName;

    @Column
    String statusCode;

    @Column
    String statusName;

    @PostPersist
    private void postPersist() {
        if(!StringUtils.hasText(statusCode)) {
            statusCode = ELicenseStatus.ACTIVE.getCode();
            statusName = ELicenseStatus.ACTIVE.getName();
        }
    }
}

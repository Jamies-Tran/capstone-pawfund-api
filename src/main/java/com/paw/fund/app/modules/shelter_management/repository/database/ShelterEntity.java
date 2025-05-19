package com.paw.fund.app.modules.shelter_management.repository.database;

import com.paw.fund.app.modules.auditable_management.repository.database.AuditableEntity;
import com.paw.fund.enums.EShelterStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shelters")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShelterEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long shelterId;

    @Column
    Long accountRoleId;

    @Column
    String shelterCode;

    @Column
    String shelterName;

    @Column
    String description;

    @Column
    Integer maximumPetCapacity;

    @Column
    LocalDateTime dateOfPub;

    @Column
    String email;

    @Column
    String hotline;

    @Column
    String address;

    @Column
    String ward;

    @Column
    String district;

    @Column
    String province;

    @Column
    BigDecimal latitude;

    @Column
    BigDecimal longitude;

    @Column
    String statusCode;

    @Column
    String statusName;

    @PrePersist
    public void prePersist() {
        if(!StringUtils.hasText(statusCode)) {
            statusCode = EShelterStatus.ENABLE.getCode();
            statusName = EShelterStatus.ENABLE.getName();
        }
    }
}

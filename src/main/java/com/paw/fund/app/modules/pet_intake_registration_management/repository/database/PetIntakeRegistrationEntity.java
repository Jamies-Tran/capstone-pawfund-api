package com.paw.fund.app.modules.pet_intake_registration_management.repository.database;

import com.paw.fund.common.context.auditor.repository.database.AuditableEntity;
import com.paw.fund.enums.EPetIntakeRegistrationStatus;
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
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pet_intake_requests")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PetIntakeRegistrationEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_intake_request_id")
    Long petIntakeRegistrationId;

    @Column
    Long petTypeId;

    @Column
    String informerPhone;

    @Column
    String petDescription;

    @Column
    String reasonTypeCode;

    @Column
    String reasonTypeName;

    @Column
    String address;

    @Column
    BigDecimal latitude;

    @Column
    BigDecimal longitude;

    @Column
    String cancelReason;

    @Column
    String statusCode;

    @Column
    String statusName;

    @PrePersist
    private void prePersist() {
        if(!StringUtils.hasText(statusCode)) {
            statusCode = EPetIntakeRegistrationStatus.NEW.getCode();
            statusName = EPetIntakeRegistrationStatus.NEW.getName();
        }
    }
}

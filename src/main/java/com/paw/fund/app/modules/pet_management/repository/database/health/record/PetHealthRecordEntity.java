package com.paw.fund.app.modules.pet_management.repository.database.health.record;

import com.paw.fund.app.modules.auditable_management.repository.database.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pet_health_records")
public class PetHealthRecordEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long petHealthRecordId;

    @Column
    Long petId;

    @Column
    LocalDateTime checkupDate;

    @Column
    BigDecimal weight;

    @Column
    String conditionDescription;

    @Column
    String treatment;

    @Column
    String diagnosis;

    @Column
    String healthStatusCode;

    @Column
    String healthStatusName;
}

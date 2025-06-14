package com.paw.fund.app.modules.shelter_assignment_management.repository.database;

import com.paw.fund.common.context.auditor.repository.database.AuditableEntity;
import com.paw.fund.enums.EShelterAssignmentStatus;
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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shelter_assignments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShelterAssignmentEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long shelterAssignmentId;

    @Column
    Long shelterId;

    @Column(name = "pet_intake_request_id")
    Long petIntakeRegistrationId;

    @Column
    String cancelReason;

    @Column
    String statusCode;

    @Column
    String statusName;

    @PrePersist
    private void prePersist() {
        if(!StringUtils.hasText(statusCode)) {
            statusCode = EShelterAssignmentStatus.NEW.getCode();
            statusName = EShelterAssignmentStatus.NEW.getName();
        }
    }

}

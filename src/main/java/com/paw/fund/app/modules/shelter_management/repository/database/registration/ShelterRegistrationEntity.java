package com.paw.fund.app.modules.shelter_management.repository.database.registration;

import com.paw.fund.app.modules.auditable_management.repository.database.AuditableEntity;
import com.paw.fund.enums.EShelterRegistrationStatus;
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

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shelter_requests")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShelterRegistrationEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelter_request_id")
    Long shelterRegistrationId;

    @Column
    Long shelterId;

    @Column
    Long accountId;

    @Column
    Long processById;

    @Column
    Long formResponseId;

    @Column
    String reason;

    @Column
    LocalDateTime requestAt;

    @Column
    LocalDateTime receivedAt;

    @Column
    LocalDateTime approvedAt;

    @Column
    LocalDateTime rejectedAt;

    @Column
    String statusCode;

    @Column
    String statusName;

    @PrePersist
    public void prePersist() {
        if(!StringUtils.hasText(statusCode)) {
            statusCode = EShelterRegistrationStatus.NEW.getCode();
            statusName = EShelterRegistrationStatus.NEW.getName();
        }
    }
}

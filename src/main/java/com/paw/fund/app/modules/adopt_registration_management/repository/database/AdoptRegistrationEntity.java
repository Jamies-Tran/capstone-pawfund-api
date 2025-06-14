package com.paw.fund.app.modules.adopt_registration_management.repository.database;

import com.paw.fund.common.context.auditor.repository.database.AuditableEntity;
import com.paw.fund.enums.EAdoptRegistrationStatus;
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
@Table(name = "adoption_requests")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdoptRegistrationEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adoption_request_id")
    Long adoptRegistrationId;

    @Column
    Long petId;

    @Column
    Long accountRoleId;

    @Column(name = "form_response_id")
    Long formReplyId;

    @Column
    String denyReason;

    @Column
    String statusCode;

    @Column
    String statusName;

    @PrePersist
    public void prePersist() {
        if(!StringUtils.hasText(statusCode)) {
            statusCode = EAdoptRegistrationStatus.NEW.getCode();
            statusName = EAdoptRegistrationStatus.NEW.getName();
        }
    }
}

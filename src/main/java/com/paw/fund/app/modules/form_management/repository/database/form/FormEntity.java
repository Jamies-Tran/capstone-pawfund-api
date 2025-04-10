package com.paw.fund.app.modules.form_management.repository.database.form;

import com.paw.fund.app.modules.auditable_management.repository.database.AuditableEntity;
import com.paw.fund.enums.EFormStatus;
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
import org.springframework.util.StringUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "forms")
public class FormEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long formId;

    @Column
    String title;

    @Column
    String description;

    @Column
    String formTypeCode;

    @Column
    String formTypeName;

    @Column
    String statusCode;

    @Column
    String statusName;

    @PrePersist
    public void prePersist() {
        if(!StringUtils.hasText(statusCode)) {
            statusCode = EFormStatus.ENABLE.getCode();
            statusName = EFormStatus.ENABLE.getName();
        }
    }
}

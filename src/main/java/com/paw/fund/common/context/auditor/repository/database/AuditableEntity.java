package com.paw.fund.common.context.auditor.repository.database;

import com.paw.fund.common.context.auditor.AuditorContext;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class AuditableEntity {
    @Column
    String createdBy;
    
    @Column
    LocalDateTime createdAt;

    @Column
    String updatedBy;

    @Column
    LocalDateTime updatedAt;

    @PrePersist
    private void preSave() {
        String auditor = AuditorContext.getCurrentAuditor().principal();

        this.setUpdatedAt(LocalDateTime.now());
        this.setCreatedAt(LocalDateTime.now());
        this.setCreatedBy(auditor);
        this.setUpdatedBy(auditor);
    }

    @PreUpdate
    private void preUpdate() {
        String auditor = AuditorContext.getCurrentAuditor().principal();

        this.setUpdatedAt(LocalDateTime.now());
        this.setUpdatedBy(auditor);
    }
}
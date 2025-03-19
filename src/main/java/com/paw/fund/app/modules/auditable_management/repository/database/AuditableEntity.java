package com.paw.fund.app.modules.auditable_management.repository.database;

import com.paw.fund.app.modules.account_management.domain.Account;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class AuditableEntity {
    @Column
    Long createdById;

    @Column
    String createdByName;

    @Column
    LocalDateTime createdAt;

    @Column
    Long updatedById;

    @Column
    String updatedByName;

    @Column
    LocalDateTime updatedAt;

    public void prepareSave(AuditableEntity auditable) {
        this.setCreatedById(auditable.getCreatedById());
        this.setCreatedByName(auditable.getCreatedByName());
        this.setCreatedAt(auditable.getCreatedAt());
        this.setUpdatedById(auditable.getUpdatedById());
        this.setUpdatedByName(auditable.getUpdatedByName());
        this.setUpdatedAt(auditable.getUpdatedAt());
    }

    public void prepareUpdate(AuditableEntity auditable) {
        this.setUpdatedById(auditable.getUpdatedById());
        this.setUpdatedByName(auditable.getUpdatedByName());
        this.setUpdatedAt(auditable.getUpdatedAt());
    }
}

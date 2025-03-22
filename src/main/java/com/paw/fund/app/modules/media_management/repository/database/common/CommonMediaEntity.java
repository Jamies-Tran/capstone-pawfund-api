package com.paw.fund.app.modules.media_management.repository.database.common;

import com.paw.fund.app.modules.auditable_management.domain.Auditable;
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

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "common_medias")
public class CommonMediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commonMediaId;

    @Column
    Long accountId;

    @Column
    Long reviewId;

    @Column
    Long petId;

    @Column
    Long shelterId;

    @Column
    String url;

    @Column
    Boolean isThumbnail;

    @Column
    String mediaTypeCode;

    @Column
    String mediaTypeName;

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

    public void prepareSave(Auditable auditable) {
        this.setCreatedById(auditable.createdById());
        this.setCreatedByName(auditable.createdByName());
        this.setCreatedAt(auditable.createdAt());
        this.setUpdatedById(auditable.updatedById());
        this.setUpdatedByName(auditable.updatedByName());
        this.setUpdatedAt(auditable.updatedAt());
    }

    public void prepareUpdate(Auditable auditable) {
        this.setUpdatedById(auditable.updatedById());
        this.setUpdatedByName(auditable.updatedByName());
        this.setUpdatedAt(auditable.updatedAt());
    }
}

package com.paw.fund.app.modules.media_management.repository.database.common;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "common_medias")
public class CommonMediaEntity extends AuditableEntity {
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
}

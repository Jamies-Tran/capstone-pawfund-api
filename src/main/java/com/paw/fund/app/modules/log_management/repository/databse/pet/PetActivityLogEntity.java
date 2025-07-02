package com.paw.fund.app.modules.log_management.repository.databse.pet;

import com.paw.fund.common.context.auditor.repository.database.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pet_activity_logs")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PetActivityLogEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long petActivityLogId;

    @Column
    Long petId;

    @Column
    String actionCode;

    @Column
    String actionName;

    @Column
    String description;
}
